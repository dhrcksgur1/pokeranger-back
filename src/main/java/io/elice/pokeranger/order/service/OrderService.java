package io.elice.pokeranger.order.service;

import io.elice.pokeranger.order.entity.OrderRequestDTO;
import io.elice.pokeranger.order.entity.OrderResponseDTO;
import io.elice.pokeranger.order.entity.Orders;
import io.elice.pokeranger.order.mapper.OrderMapper;
import io.elice.pokeranger.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO){
        Orders order = orderMapper.OrderRequestDTOToOrder(orderRequestDTO);
        Orders savedOrder = orderRepository.save(order);
        return orderMapper.OrderToOrderResponseDTO(savedOrder);
    }
}
