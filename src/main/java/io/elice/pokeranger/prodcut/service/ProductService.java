package io.elice.pokeranger.prodcut.service;

import io.elice.pokeranger.prodcut.entity.Product;
import io.elice.pokeranger.prodcut.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

//    public Optional<Product> findProductById(Long id) {
//        return productRepository.findById(id);
//    } Optional<Product> VS Product 차이
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
