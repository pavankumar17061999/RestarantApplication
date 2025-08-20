package com.Pavan.foodcatalougue.f.service;

import com.Pavan.foodcatalougue.f.dto.FoodCataloguePage;
import com.Pavan.foodcatalougue.f.dto.FoodItemDTO;
import com.Pavan.foodcatalougue.f.dto.RestaurantDTO;
import com.Pavan.foodcatalougue.f.entity.FoodItem;
import com.Pavan.foodcatalougue.f.mapper.FoodItemMapper;
import com.Pavan.foodcatalougue.f.repo.FoodItemRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodCatalogueService {

    private final FoodItemRepo foodItemRepo;
    private final RestTemplate restTemplate;
    private final FoodItemMapper foodItemMapper;

    public FoodCatalogueService(FoodItemRepo foodItemRepo,
                                RestTemplate restTemplate,
                                FoodItemMapper foodItemMapper) {
        this.foodItemRepo = foodItemRepo;
        this.restTemplate = restTemplate;
        this.foodItemMapper = foodItemMapper;
    }

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem entity = foodItemMapper.mapFoodItemDTOToFoodItem(foodItemDTO);
        FoodItem savedEntity = foodItemRepo.save(entity);
        return foodItemMapper.mapFoodItemToFoodItemDto(savedEntity);
    }

    public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId) {
        List<FoodItemDTO> foodItems = fetchFoodItemList(restaurantId);
        RestaurantDTO restaurant = fetchRestaurantDetailsFromRestaurantMS(restaurantId);
        return new FoodCataloguePage(foodItems, restaurant);
    }

    private RestaurantDTO fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
        // Updated URL to match RestaurantController endpoint
        return restTemplate.getForObject(
                "http://RESTAURANT-SERVICE/restaurants/" + restaurantId,
                RestaurantDTO.class
        );
    }

    private List<FoodItemDTO> fetchFoodItemList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId)
                .stream()
                .map(foodItemMapper::mapFoodItemToFoodItemDto)
                .collect(Collectors.toList());
    }
}
