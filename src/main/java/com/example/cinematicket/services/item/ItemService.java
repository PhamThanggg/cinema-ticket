package com.example.cinematicket.services.item;

import com.example.cinematicket.dtos.requests.ItemRequest;
import com.example.cinematicket.dtos.responses.ItemResponse;
import com.example.cinematicket.entities.Item;
import com.example.cinematicket.mapper.ItemMapper;
import com.example.cinematicket.repositories.ItemRepository;
import com.example.cinematicket.repositories.MovieRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemService implements IItemService {
    ItemRepository itemRepository;
    ItemMapper itemMapper;
    MovieRepository movieRepository;
    @Override
    public ItemResponse createItem(ItemRequest request) {
        if(movieRepository.existsById(request.getIdCinema())){
            throw  new RuntimeException("Cinema not exists");
        }
        if(itemRepository.existsByName(request.getName())){
            throw  new RuntimeException("Item exists");
        }

        Item item = itemMapper.toItem(request);
        return itemMapper.toItemResponse(itemRepository.save(item));
    }

    @Override
    public List<ItemResponse> getItemByCinema(Long cinemaId) {
        return itemRepository.findByIdCinema(cinemaId).stream().map(itemMapper::toItemResponse).toList();
    }

    @Override
    public ItemResponse updateItem(ItemRequest request, Long id) {
        if(movieRepository.existsById(request.getIdCinema())){
            throw  new RuntimeException("Cinema not exists");
        }
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not exists"));

        if(!item.getName().equals(request.getName())){
            if(itemRepository.existsByName(request.getName())){
                throw  new RuntimeException("Item exists");
            }
        }

        itemMapper.updateItem(item, request);
        return itemMapper.toItemResponse(itemRepository.save(item));
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
