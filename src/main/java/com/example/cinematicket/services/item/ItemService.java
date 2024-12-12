package com.example.cinematicket.services.item;

import com.example.cinematicket.dtos.requests.ItemRequest;
import com.example.cinematicket.dtos.responses.item.ItemResponse;
import com.example.cinematicket.entities.Item;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.ItemMapper;
import com.example.cinematicket.repositories.CinemaRepository;
import com.example.cinematicket.repositories.ItemRepository;
import com.example.cinematicket.repositories.MovieRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemService implements IItemService {
    ItemRepository itemRepository;
    ItemMapper itemMapper;
    CinemaRepository cinemaRepository;
    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_ITEM')")
    public ItemResponse createItem(ItemRequest request) {
        if(!cinemaRepository.existsById(request.getIdCinema())){
            throw new AppException(ErrorCode.CINEMA_NOT_EXISTED);
        }
        if(itemRepository.existsByNameAndIdCinema(request.getName(), request.getIdCinema())){
            throw new AppException(ErrorCode.ITEM_EXISTS);
        }

        Item item = itemMapper.toItem(request);
        return itemMapper.toItemResponse(itemRepository.save(item));
    }

    @Override
    public List<ItemResponse> getItemByCinema(Long cinemaId) {
        return itemRepository.findByIdCinema(cinemaId).stream().map(itemMapper::toItemResponse).toList();
    }

    public ItemResponse getItemById(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new AppException(ErrorCode.ITEM_NOT_EXISTS));

        return itemMapper.toItemResponse(item);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_ITEM')")
    public ItemResponse updateItem(ItemRequest request, Long id) {
        if(!cinemaRepository.existsById(request.getIdCinema())){
            throw new AppException(ErrorCode.CINEMA_NOT_EXISTED);
        }
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ITEM_NOT_EXISTS));

        if(!item.getName().equals(request.getName())){
            if(itemRepository.existsByNameAndIdCinema(request.getName(), request.getIdCinema())){
                throw new AppException(ErrorCode.ITEM_EXISTS);
            }
        }

        itemMapper.updateItem(item, request);
        return itemMapper.toItemResponse(itemRepository.save(item));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_ITEM')")
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
