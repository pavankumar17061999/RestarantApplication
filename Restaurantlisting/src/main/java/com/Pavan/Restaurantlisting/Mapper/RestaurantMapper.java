package com.Pavan.Restaurantlisting.Mapper;

import com.Pavan.Restaurantlisting.dto.RestaurantDTO;
import com.Pavan.Restaurantlisting.entity.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    Restaurant mapRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO);
    RestaurantDTO mapRestaurantToRestaurantDTO(Restaurant restaurant);
}
