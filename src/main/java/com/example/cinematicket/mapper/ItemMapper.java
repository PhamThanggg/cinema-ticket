package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.ItemRequest;
import com.example.cinematicket.dtos.responses.ItemResponse;
import com.example.cinematicket.entities.Item;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item toItem(ItemRequest request);

    ItemResponse toItemResponse(Item item);

    void updateItem(@MappingTarget Item item, ItemRequest request);
}
