package io.elice.pokeranger.order.mapper;

import io.elice.pokeranger.order.entity.OrderRequestDTO;
import io.elice.pokeranger.order.entity.OrderResponseDTO;
import io.elice.pokeranger.order.entity.Orders;
import io.elice.pokeranger.orderItem.entity.CartItemDTO;
import io.elice.pokeranger.orderItem.entity.OrderItem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-23T20:52:06+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Orders OrderRequestDTOToOrder(OrderRequestDTO orderRequestDTO) {
        if ( orderRequestDTO == null ) {
            return null;
        }

        Orders orders = new Orders();

        orders.setOrderMessage( orderRequestDTO.getOrderMessage() );
        orders.setTotalCost( orderRequestDTO.getTotalCost() );

        return orders;
    }

    @Override
    public OrderResponseDTO OrderToOrderResponseDTO(Orders orders) {
        if ( orders == null ) {
            return null;
        }

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        if ( orders.getDeliveryState() != null ) {
            orderResponseDTO.setDeliveryState( orders.getDeliveryState().name() );
        }
        orderResponseDTO.setOrderMessage( orders.getOrderMessage() );

        return orderResponseDTO;
    }

    @Override
    public List<OrderItem> cartItemDTOListToOrderItemList(List<CartItemDTO> cartItemDTOList) {
        if ( cartItemDTOList == null ) {
            return null;
        }

        List<OrderItem> list = new ArrayList<OrderItem>( cartItemDTOList.size() );
        for ( CartItemDTO cartItemDTO : cartItemDTOList ) {
            list.add( cartItemDTOToOrderItem( cartItemDTO ) );
        }

        return list;
    }

    protected OrderItem cartItemDTOToOrderItem(CartItemDTO cartItemDTO) {
        if ( cartItemDTO == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setQuantity( cartItemDTO.getQuantity() );

        return orderItem;
    }
}
