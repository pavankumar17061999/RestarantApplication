package controller;

import com.Pavan.Restaurantlisting.controller.RestaurantController;
import com.Pavan.Restaurantlisting.dto.RestaurantDTO;
import com.Pavan.Restaurantlisting.exception.RestaurantNotFoundException;
import com.Pavan.Restaurantlisting.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @Autowired
    private ObjectMapper objectMapper;

    private RestaurantDTO restaurantDTO;

    @BeforeEach
    void setUp() {
        restaurantDTO = new RestaurantDTO(1, "Test Restaurant", "123 Street", "TestCity", "Desc");
    }

    @Test
    void getAllRestaurants_ShouldReturnOkAndList() throws Exception {
        when(restaurantService.findAllRestaurants()).thenReturn(List.of(restaurantDTO));

        mockMvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Restaurant"));

        verify(restaurantService).findAllRestaurants();
    }

    @Test
    void getRestaurantById_WhenFound_ShouldReturnOkAndDTO() throws Exception {
        when(restaurantService.findRestaurantById(1)).thenReturn(Optional.of(restaurantDTO));

        mockMvc.perform(get("/restaurants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Restaurant"));

        verify(restaurantService).findRestaurantById(1);
    }

    @Test
    void getRestaurantById_WhenNotFound_ShouldReturnNotFound() throws Exception {
        when(restaurantService.findRestaurantById(2)).thenReturn(Optional.empty());

        mockMvc.perform(get("/restaurants/2"))
                .andExpect(status().isNotFound());

        verify(restaurantService).findRestaurantById(2);
    }

    @Test
    void createRestaurant_ShouldReturnCreatedAndDTO() throws Exception {
        when(restaurantService.addRestaurantInDB(any(RestaurantDTO.class))).thenReturn(restaurantDTO);

        mockMvc.perform(post("/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurantDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Restaurant"));

        verify(restaurantService).addRestaurantInDB(any(RestaurantDTO.class));
    }

    @Test
    void updateRestaurant_WhenFound_ShouldReturnOkAndUpdatedDTO() throws Exception {
        RestaurantDTO updatedDTO = new RestaurantDTO(1, "Updated Name", "123 Street", "TestCity", "Desc");
        when(restaurantService.updateRestaurant(eq(1), any(RestaurantDTO.class))).thenReturn(Optional.of(updatedDTO));

        mockMvc.perform(put("/restaurants/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"));

        verify(restaurantService).updateRestaurant(eq(1), any(RestaurantDTO.class));
    }

    @Test
    void updateRestaurant_WhenNotFound_ShouldReturnNotFound() throws Exception {
        when(restaurantService.updateRestaurant(eq(2), any(RestaurantDTO.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/restaurants/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurantDTO)))
                .andExpect(status().isNotFound());

        verify(restaurantService).updateRestaurant(eq(2), any(RestaurantDTO.class));
    }

    @Test
    void deleteRestaurant_WhenFound_ShouldReturnNoContent() throws Exception {
        when(restaurantService.deleteRestaurantById(1)).thenReturn(true);

        mockMvc.perform(delete("/restaurants/1"))
                .andExpect(status().isNoContent());

        verify(restaurantService).deleteRestaurantById(1);
    }

    @Test
    void deleteRestaurant_WhenNotFound_ShouldReturnNotFound() throws Exception {
        when(restaurantService.deleteRestaurantById(2)).thenReturn(false);

        mockMvc.perform(delete("/restaurants/2"))
                .andExpect(status().isNotFound());

        verify(restaurantService).deleteRestaurantById(2);
    }

    @Test
    void getByCity_ShouldReturnOkAndList() throws Exception {
        when(restaurantService.findByCity("TestCity")).thenReturn(List.of(restaurantDTO));

        mockMvc.perform(get("/restaurants/city/TestCity"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].city").value("TestCity"));

        verify(restaurantService).findByCity("TestCity");
    }
}
