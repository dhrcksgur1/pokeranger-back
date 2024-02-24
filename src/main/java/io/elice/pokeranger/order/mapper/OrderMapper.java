package io.elice.pokeranger.order.mapper;

import io.elice.pokeranger.order.entity.OrderRequestDTO;
import io.elice.pokeranger.order.entity.OrderResponseDTO;
import io.elice.pokeranger.order.entity.Orders;
import io.elice.pokeranger.orderItem.entity.CartItemDTO;
import io.elice.pokeranger.orderItem.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "orderDate", ignore = true)
    Orders OrderRequestDTOToOrder(OrderRequestDTO orderRequestDTO);

    OrderResponseDTO OrderToOrderResponseDTO(Orders orders);

    List<OrderItem> cartItemDTOListToOrderItemList(List<CartItemDTO> cartItemDTOList);

}
