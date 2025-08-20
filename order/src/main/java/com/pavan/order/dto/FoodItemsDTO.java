package com.pavan.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemsDTO {

    private int id;

    private String itemName;

    private String itemDescription;

    private boolean isVeg;

    private Number price;

    private Integer restaurantId;

    private Integer quantity;

}