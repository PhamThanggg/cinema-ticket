package com.example.cinematicket.services.item;

import com.example.cinematicket.dtos.requests.AreaRequest;
import com.example.cinematicket.dtos.requests.ItemRequest;
import com.example.cinematicket.dtos.responses.AreaResponse;
import com.example.cinematicket.dtos.responses.ItemResponse;

import java.util.List;

public interface IItemService {
    ItemResponse createItem(ItemRequest request);

    List<ItemResponse> getItemByCinema(Long cinemaId);

    ItemResponse updateItem(ItemRequest request, Long id);

    void deleteItem(Long id);
}
