package com.Pavan.Restaurantlisting.service;

import com.Pavan.Restaurantlisting.Mapper.RestaurantMapper;
import com.Pavan.Restaurantlisting.dto.RestaurantDTO;
import com.Pavan.Restaurantlisting.entity.Restaurant;
import com.Pavan.Restaurantlisting.exception.RestaurantNotFoundException;
import com.Pavan.Restaurantlisting.repo.RestaurantRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RestaurantService {

    private final RestaurantRepo restaurantRepo;
    private final RestaurantMapper restaurantMapper;

    // ✅ Constructor injection for Mapper and Repo
    public RestaurantService(RestaurantRepo restaurantRepo, RestaurantMapper restaurantMapper) {
        this.restaurantRepo = restaurantRepo;
        this.restaurantMapper = restaurantMapper;
    }

    // ✅ Fetch all restaurants
    public List<RestaurantDTO> findAllRestaurants() {
        log.info("Fetching all restaurants");
        return restaurantRepo.findAll()
                .stream()
                .map(restaurantMapper::mapRestaurantToRestaurantDTO)
                .toList();
    }

    // ✅ Create new restaurant
    public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
        log.info("Adding new restaurant: {}", restaurantDTO.getName());
        Restaurant entity = restaurantMapper.mapRestaurantDTOToRestaurant(restaurantDTO);
        Restaurant saved = restaurantRepo.save(entity);
        return restaurantMapper.mapRestaurantToRestaurantDTO(saved);
    }

    // ✅ Get restaurant by ID
    public Optional<RestaurantDTO> findRestaurantById(Integer id) {
        log.info("Finding restaurant by ID: {}", id);
        return restaurantRepo.findById(id)
                .map(restaurantMapper::mapRestaurantToRestaurantDTO);
    }

    // ✅ Update restaurant by ID
    public Optional<RestaurantDTO> updateRestaurant(Integer id, RestaurantDTO dto) {
        log.info("Updating restaurant ID: {}", id);
        return restaurantRepo.findById(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    existing.setAddress(dto.getAddress());
                    existing.setCity(dto.getCity());
                    existing.setRestaurantDescription(dto.getRestaurantDescription());
                    Restaurant updated = restaurantRepo.save(existing);
                    return restaurantMapper.mapRestaurantToRestaurantDTO(updated);
                });
    }

    // ✅ Delete restaurant by ID
    public boolean deleteRestaurantById(Integer id) {
        log.info("Deleting restaurant ID: {}", id);
        if (restaurantRepo.existsById(id)) {
            restaurantRepo.deleteById(id);
            return true;
        }
        return false;
    }

    // ✅ Find restaurants by city
    public List<RestaurantDTO> findByCity(String city) {
        log.info("Finding restaurants by city: {}", city);
        return restaurantRepo.findByCityIgnoreCase(city)
                .stream()
                .map(restaurantMapper::mapRestaurantToRestaurantDTO)
                .toList();
    }
}
