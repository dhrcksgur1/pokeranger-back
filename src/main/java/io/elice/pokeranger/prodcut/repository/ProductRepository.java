package io.elice.pokeranger.prodcut.repository;

import io.elice.pokeranger.prodcut.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
