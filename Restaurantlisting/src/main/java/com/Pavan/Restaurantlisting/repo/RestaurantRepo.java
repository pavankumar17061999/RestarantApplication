package com.Pavan.Restaurantlisting.repo;

import com.Pavan.Restaurantlisting.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {
    List<Restaurant> findByCityIgnoreCase(String city);
}
