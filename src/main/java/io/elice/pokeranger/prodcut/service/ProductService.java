package io.elice.pokeranger.prodcut.service;

import io.elice.pokeranger.prodcut.entity.Product;
import io.elice.pokeranger.prodcut.entity.ProductRequestDTO;
import io.elice.pokeranger.prodcut.entity.ProductResponseDTO;
import io.elice.pokeranger.prodcut.mapper.ProductMapper;
import io.elice.pokeranger.prodcut.repository.ProductRepository;
import io.elice.pokeranger.user.entity.User;
import io.elice.pokeranger.user.repository.UserRepository;
import io.elice.pokeranger.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper, UserService userService,UserRepository userRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    //CREATE 완성코드
    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO productDto) {
        User user = userRepository.findById(productDto.getUserId()).orElse(null);

        Product product = new Product(
                user,
                productDto.getName(),
                productDto.getPrice(),
                productDto.getStock(),
                productDto.getDescription(),
                productDto.getImages()
        );
        productRepository.save(product);
        return productMapper.productToDto(product);
    }


    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

//    //userID로 검색(미구현)
    public List<Product> getProductsByUserId(Long userId) {
        return productRepository.findByUserId(userId);
    }


    //UPDATE
    @Transactional
    public Product updateProduct(Long productId, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + productId));
//        User user = userService.getUserById(dto.getUserId());
//        product.setUser(user);
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setStock(productRequestDTO.getStock());
        product.setDescription(productRequestDTO.getDescription());
        product.setImages(productRequestDTO.getImages());
        return productRepository.save(product);
    }

 // DELETE
    @Transactional
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    //create 엔티티
//    @Transactional
//    public Product save(Product product) {
//        return productRepository.save(product);
//    }


    //create dto
//    @Transactional
//    public ProductResponseDTO createProduct(ProductResponseDTO productDto) {
//        Product product = productMapper.dtoToProduct(productDto);
//        Product savedProduct = productRepository.save(product);
//        return productMapper.productToDto(savedProduct);
//    }

//    //userID가져와서 삭제 구현(미구현)
//    @Transactional
//    public void deleteProductById(Long id) {
//        productRepository.deleteById(id);
//    }


}
