package com.Pavan.foodcatalougue.f.mapper;

import com.Pavan.foodcatalougue.f.dto.FoodItemDTO;
import com.Pavan.foodcatalougue.f.entity.FoodItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodItemMapper {

    FoodItem mapFoodItemDTOToFoodItem(FoodItemDTO foodItemDTO);

    FoodItemDTO mapFoodItemToFoodItemDto(FoodItem foodItem);
}
