package com.pavan.order.entity;

import com.pavan.order.dto.FoodItemsDTO;
import com.pavan.order.dto.Restaurant;
import com.pavan.order.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Order {
    private Integer orderId;
    private List<FoodItemsDTO> foodItemsList;
    private Restaurant restaurant;
    private UserDTO userDTO;

}

