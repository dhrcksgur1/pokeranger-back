package io.elice.pokeranger.order.service;

import io.elice.pokeranger.order.entity.OrderRequestDTO;
import io.elice.pokeranger.order.entity.OrderResponseDTO;
import io.elice.pokeranger.order.entity.Orders;
import io.elice.pokeranger.order.mapper.OrderMapper;
import io.elice.pokeranger.order.repository.OrderRepository;
import io.elice.pokeranger.orderItem.entity.CartItemDTO;
import io.elice.pokeranger.orderItem.entity.OrderItem;
import io.elice.pokeranger.orderItem.repository.OrderItemRepository;
import io.elice.pokeranger.prodcut.entity.Product;
import io.elice.pokeranger.prodcut.repository.ProductRepository;
import io.elice.pokeranger.user.entity.User;
import io.elice.pokeranger.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO){
        User user = userRepository.findById(orderRequestDTO.getUserId()).orElse(null);
        Orders order = new Orders(
                orderRequestDTO.getOrderMessage(),
                orderRequestDTO.getTotalCost(),
                user
        );
        for (CartItemDTO cartItem : orderRequestDTO.getCartItems()) {
            Product product = productRepository.findById(cartItem.getProductId()).orElse(null);
            if (product != null) {
                OrderItem orderItem = new OrderItem(product, order, cartItem.getQuantity());
                order.getItems().add(orderItem);
            }
        }
        orderRepository.save(order);
        return orderMapper.OrderToOrderResponseDTO(order);
    }
}
