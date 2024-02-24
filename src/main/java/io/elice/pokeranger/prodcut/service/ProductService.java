package io.elice.pokeranger.prodcut.service;

import io.elice.pokeranger.prodcut.entity.Product;
import io.elice.pokeranger.prodcut.entity.ProductDto;
import io.elice.pokeranger.prodcut.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    //create 에러발생(엔티티가 아닌 DTO로 받기위해 수정중, @getter가 있음에도 오류발생 이유를 모르겠습니다.)
//    public Product save(ProductDto productDto) {
//        Product product = new Product(
//                productDto.getName(),
//                productDto.getDescription(),
//                productDto.getPrice(),
//                productDto.getStock()
//        );
//        return productRepository.save(product);
//    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
