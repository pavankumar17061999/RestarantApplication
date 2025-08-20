package com.Pavan.foodcatalougue.f.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodCataloguePage {

    private List<FoodItemDTO> foodItemList;     // Use DTOs, not entities!
    private RestaurantDTO restaurant;           // Use DTO that matches external API or your MS boundary
}
