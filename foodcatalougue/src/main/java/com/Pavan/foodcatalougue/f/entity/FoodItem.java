package com.Pavan.foodcatalougue.f.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "food_items") // Optional, for clarity and naming
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;                // Use Integer

    private String itemName;
    private String itemDescription;
    private boolean veg;               // Or 'isVeg' if you prefer

    private BigDecimal price;          // Use BigDecimal for money

    private Integer restaurantId;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer quantity = 0;      // Initialize default in Java
}
