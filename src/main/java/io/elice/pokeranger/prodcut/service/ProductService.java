package io.elice.pokeranger.prodcut.service;

import io.elice.pokeranger.prodcut.entity.Product;
import io.elice.pokeranger.prodcut.entity.ProductDto;
import io.elice.pokeranger.prodcut.mapper.ProductMapper;
import io.elice.pokeranger.prodcut.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository,ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    //create 엔티티
    public Product save(Product product) {
        return productRepository.save(product);
    }

    //create dto
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.dtoToProduct(productDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.productToDto(savedProduct);
    }

//    오류발생
//    public ProductDto createProduct(ProductDto productDto) {
//        Product product = productMapper.dtoToProduct(productDto);
//        return productRepository.save(product);
//    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
