package io.elice.pokeranger.prodcut.service;

import io.elice.pokeranger.category.entity.Category;
import io.elice.pokeranger.category.repository.CategoryRepository;
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
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper, UserService userService,UserRepository userRepository,CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.userService = userService;
        this.userRepository = userRepository;
        this.categoryRepository =categoryRepository;
    }

    //CREATE 완성코드
    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO productDto) {
        User user = userRepository.findById(productDto.getUserId()).orElse(null);
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);

        Product product = new Product(
                user,
                category,
                productDto.getName(),
                productDto.getPrice(),
                productDto.getStock(),
                productDto.getDescription(),
                productDto.getImages()
        );
        productRepository.save(product);
        return productMapper.productToDto(product);
    }


    //기존코드
//    public List<Product> findAllProducts(){
//        return productRepository.findAll();
//    }

    //수정코드
    public List<ProductResponseDTO> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::productToDto)
                .collect(Collectors.toList());
    }


    //기존코드
//    public Optional<Product> findProductById(Long id) {
//        return productRepository.findById(id);
//    }

    //수정코드
    public ProductResponseDTO findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));
        return productMapper.productToDto(product);
    }


//    //userID로 검색(미구현)

//    public List<Product> getProductsByUserId(Long userId) {
//        return productRepository.findByUserId(userId);
//    }

    //수정코드
    public List<ProductResponseDTO> getProductsByUserId(Long userId) {
        List<Product> products = productRepository.findByUserId(userId);
        return products.stream()
                .map(productMapper::productToDto)
                .collect(Collectors.toList());
    }


    public List<ProductResponseDTO> getProductsByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        List<ProductResponseDTO> productResponseDTOs = new ArrayList<>();
        for (Product product : products) {
            productResponseDTOs.add(productMapper.productToDto(product));
        }
        return productResponseDTOs;
    }


    //UPDATE
//    @Transactional//기존코드
//    public Product updateProduct(Long productId, ProductRequestDTO productRequestDTO) {
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + productId));
//        product.setName(productRequestDTO.getName());
//        product.setPrice(productRequestDTO.getPrice());
//        product.setStock(productRequestDTO.getStock());
//        product.setDescription(productRequestDTO.getDescription());
//        product.setImages(productRequestDTO.getImages());
//        return productRepository.save(product);
//    }

    @Transactional//수정코드
    public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + productId));
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setStock(productRequestDTO.getStock());
        product.setDescription(productRequestDTO.getDescription());
        product.setImages(productRequestDTO.getImages());
        Product updatedProduct = productRepository.save(product);
        return productMapper.productToDto(updatedProduct);
    }

 // DELETE
    @Transactional
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }


}
