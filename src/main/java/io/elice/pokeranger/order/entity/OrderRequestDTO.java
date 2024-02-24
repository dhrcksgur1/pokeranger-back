package io.elice.pokeranger.order.entity;

import io.elice.pokeranger.orderItem.entity.CartItemDTO;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {

    private Long userId;
    private String orderMessage;
    private int totalCost;
    private List<CartItemDTO> cartItems;
}
