package io.elice.pokeranger.prodcut.repository;

import io.elice.pokeranger.prodcut.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    List<Product> findByUserId(Long userId);
//    List<Product> findByCategoryId(Long categoryId);

    Page<Product> findByUserId(Long userId, Pageable pageable);
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

}
