package com.Pavan.foodcatalougue.f.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDTO {

    private Integer id;

    @NotBlank(message = "Item name is required")
    private String itemName;

    @NotBlank(message = "Item description is required")
    private String itemDescription;

    private boolean veg; // or `isVeg` if you prefer JSON property as 'isVeg'

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Restaurant ID is required")
    private Integer restaurantId;

    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;
}
