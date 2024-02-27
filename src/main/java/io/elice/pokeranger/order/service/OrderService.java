package io.elice.pokeranger.order.service;

import io.elice.pokeranger.order.deliverystate.DeliveryStateRole;
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
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        User user = userRepository.findById(orderRequestDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Orders order = new Orders(
                orderRequestDTO.getOrderMessage(),
                orderRequestDTO.getTotalCost(),
                user
        );
        for (CartItemDTO cartItem : orderRequestDTO.getCartItems()) {
            Product product = productRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));
            if (product != null) {
                OrderItem orderItem = new OrderItem(product, order, cartItem.getQuantity());
                order.getItems().add(orderItem);
            }
        }
        orderRepository.save(order);
        return orderMapper.OrderToOrderResponseDTO(order);
    }

    public List<OrderResponseDTO> getOrderList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        List<Orders> orders;
        // 유저 rols 추가시 변경
        if (false/*user.getRols() == USER*/ ){
        }else {
            orders = orderRepository.findByUserId(userId);
        }

        List<OrderResponseDTO> orderResponseDTOs = orders.stream().map(order -> {
            OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
            orderResponseDTO.setOrderDate(order.getOrderDate());
            orderResponseDTO.setDeliveryState(order.getDeliveryState());
            orderResponseDTO.setTotalCost(order.getTotalCost());

            List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getId());

            List<CartItemDTO> cartItemDTOs = orderItems.stream().map(item -> {
                CartItemDTO cartItemDTO = new CartItemDTO();
                cartItemDTO.setProductId(item.getProduct().getId());
                cartItemDTO.setQuantity(item.getQuantity());
                return cartItemDTO;
            }).collect(Collectors.toList());

            orderResponseDTO.setCartItems(cartItemDTOs);

            return orderResponseDTO;
        }).collect(Collectors.toList());

        return orderResponseDTOs;
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        if (order.getDeliveryState() == DeliveryStateRole.PREPARE){
            orderRepository.delete(order);
        }

    }

    @Transactional
    public void updateOrderState(DeliveryStateRole state, Long orderId) {
        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setDeliveryState(state);
        orderRepository.save(order);
    }
}
