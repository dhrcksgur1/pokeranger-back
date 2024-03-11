package io.elice.pokeranger.order.entity;

import io.elice.pokeranger.order.deliverystate.DeliveryStateRole;
import io.elice.pokeranger.orderItem.entity.CartItemDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDTO {
    private Long id;
    private int totalCost;
    private LocalDateTime createdAt;
    private String summaryTitle;
    private String deliveryState;
}
