package io.elice.pokeranger.orderItem.repository;

import io.elice.pokeranger.orderItem.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrderId(Long id);
    List<OrderItem> findByProductId(Long productId);

}
