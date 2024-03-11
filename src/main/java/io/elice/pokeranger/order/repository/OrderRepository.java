package io.elice.pokeranger.order.repository;

import io.elice.pokeranger.order.entity.OrderResponseDTO;
import io.elice.pokeranger.order.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    Page<Orders> findByUserId(Long userId, Pageable pageable);
}
