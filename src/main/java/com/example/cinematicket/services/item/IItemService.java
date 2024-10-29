package com.example.cinematicket.services.item;

import com.example.cinematicket.dtos.requests.ItemRequest;
import com.example.cinematicket.dtos.responses.item.ItemResponse;

import java.util.List;

public interface IItemService {
    ItemResponse createItem(ItemRequest request);

    List<ItemResponse> getItemByCinema(Long cinemaId);

    ItemResponse updateItem(ItemRequest request, Long id);

    void deleteItem(Long id);
}
