package io.elice.pokeranger.order.entity;

import io.elice.pokeranger.orderItem.entity.CartItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderRequestDTO {

    private Long userId;
    private String orderMessage;
    private AddressDTO address;
    private String summaryTitle;
    private int totalCost;
    private List<CartItemDTO> cartItems;
}
