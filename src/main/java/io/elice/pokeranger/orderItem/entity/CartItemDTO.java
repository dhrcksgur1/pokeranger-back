package io.elice.pokeranger.orderItem.entity;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long productId;
    private int quantity;
    private int totalCost;
}
