package com.Pavan.foodcatalougue.f.controller;

import com.Pavan.foodcatalougue.f.dto.FoodCataloguePage;
import com.Pavan.foodcatalougue.f.dto.FoodItemDTO;
import com.Pavan.foodcatalougue.f.service.FoodCatalogueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodCatalogue")
@CrossOrigin
public class FoodCatalogueController {

    private final FoodCatalogueService foodCatalogueService;

    public FoodCatalogueController(FoodCatalogueService foodCatalogueService) {
        this.foodCatalogueService = foodCatalogueService;
    }

    // Create new food item
    @PostMapping("/foodItems")
    public ResponseEntity<FoodItemDTO> addFoodItem(@Valid @RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDTO saved = foodCatalogueService.addFoodItem(foodItemDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Get catalogue for restaurant ID
    @GetMapping("/restaurants/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> getCatalogueByRestaurantId(@PathVariable Integer restaurantId) {
        FoodCataloguePage page = foodCatalogueService.fetchFoodCataloguePageDetails(restaurantId);
        return ResponseEntity.ok(page); // ✅ Correct status for GET
    }
}
