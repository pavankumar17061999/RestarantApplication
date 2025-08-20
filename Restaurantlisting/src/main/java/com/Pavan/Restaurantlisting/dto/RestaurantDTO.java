package com.Pavan.Restaurantlisting.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class RestaurantDTO {

    private Integer id; // Use Integer, not int, for validation compatibility

    @NotBlank(message = "Restaurant name is required")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    private String restaurantDescription;
}
