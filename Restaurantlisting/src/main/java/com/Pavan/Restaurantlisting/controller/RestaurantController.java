package com.Pavan.Restaurantlisting.controller;

import com.Pavan.Restaurantlisting.dto.RestaurantDTO;
import com.Pavan.Restaurantlisting.exception.RestaurantNotFoundException;
import com.Pavan.Restaurantlisting.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/restaurants")  // plural resource name
@CrossOrigin
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // Fetch all restaurants: GET /restaurants
    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        log.info("Fetching all restaurants");
        return ResponseEntity.ok(restaurantService.findAllRestaurants());
    }

    // Create restaurant: POST /restaurants
    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@Valid @RequestBody RestaurantDTO dto) {
        log.info("Creating restaurant: {}", dto);
        RestaurantDTO saved = restaurantService.addRestaurantInDB(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Fetch by ID: GET /restaurants/{id}
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable Integer id) {
        log.info("Fetching restaurant with ID: {}", id);
        return restaurantService.findRestaurantById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id " + id));
    }

    // Update restaurant: PUT /restaurants/{id}
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@PathVariable Integer id,
                                                          @Valid @RequestBody RestaurantDTO dto) {
        log.info("Updating restaurant with ID: {} | New data: {}", id, dto);
        return restaurantService.updateRestaurant(id, dto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id " + id));
    }

    // Delete restaurant: DELETE /restaurants/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Integer id) {
        log.info("Deleting restaurant with ID: {}", id);
        if (restaurantService.deleteRestaurantById(id)) {
            return ResponseEntity.noContent().build();
        }
        throw new RestaurantNotFoundException("Restaurant not found with id " + id);
    }

    // Search by city: GET /restaurants/city/{city}
    @GetMapping("/city/{city}")
    public ResponseEntity<List<RestaurantDTO>> getByCity(@PathVariable String city) {
        log.info("Fetching restaurants in city: {}", city);
        return ResponseEntity.ok(restaurantService.findByCity(city));
    }
}
