package io.elice.pokeranger.order.service;

import io.elice.pokeranger.order.deliverystate.DeliveryStateRole;
import io.elice.pokeranger.order.entity.OrderRequestDTO;
import io.elice.pokeranger.order.entity.OrderResponseDTO;
import io.elice.pokeranger.order.entity.OrderStateDTO;
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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static io.elice.pokeranger.enums.UserType.Admin;

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


    public Page<OrderResponseDTO> getOrderList(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Page<Orders> orders;

        if (user.getType() == Admin){
            orders = orderRepository.findAll(pageable);
        }else {
            orders = orderRepository.findByUserId(userId, pageable);
        }

        Page<OrderResponseDTO> orderResponseDTOs = orders.map(order -> {
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
        });

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
    public OrderResponseDTO updateOrderState(OrderStateDTO orderStateDTO, Long orderId) {
        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setDeliveryState(orderStateDTO.getDeliveryState());
        orderRepository.save(order);
        return orderMapper.OrderToOrderResponseDTO(order);
    }
}
