package io.elice.pokeranger.prodcut.service;

import io.elice.pokeranger.category.entity.Category;
import io.elice.pokeranger.category.repository.CategoryRepository;
import io.elice.pokeranger.global.exception.ExceptionCode;
import io.elice.pokeranger.global.exception.ServiceLogicException;
import io.elice.pokeranger.orderItem.entity.OrderItem;
import io.elice.pokeranger.orderItem.repository.OrderItemRepository;
import io.elice.pokeranger.prodcut.entity.Product;
import io.elice.pokeranger.prodcut.entity.ProductCreateDTO;
import io.elice.pokeranger.prodcut.entity.ProductRequestDTO;
import io.elice.pokeranger.prodcut.entity.ProductResponseDTO;
import io.elice.pokeranger.prodcut.mapper.ProductMapper;
import io.elice.pokeranger.prodcut.repository.ProductRepository;
import io.elice.pokeranger.user.entity.User;
import io.elice.pokeranger.user.repository.UserRepository;
import io.elice.pokeranger.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private final OrderItemRepository orderItemRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper, UserService userService,UserRepository userRepository,CategoryRepository categoryRepository,OrderItemRepository orderItemRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.userService = userService;
        this.userRepository = userRepository;
        this.categoryRepository =categoryRepository;
        this.orderItemRepository =orderItemRepository;
    }

    //CREATE
    @Transactional
    public ProductResponseDTO createProduct(ProductCreateDTO productDto) {
        User user = userRepository.findById(productDto.getUserId())
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.USER_NOT_FOUND));

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.CATEGORY_NOT_FOUND));

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

    // Read All Products
    public Page<ProductResponseDTO> findAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(productMapper::productToDto);
    }

    //Read Products By Productid
    public ProductResponseDTO findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));
        return productMapper.productToDto(product);
    }

    // Read Products by UserID
    public Page<ProductResponseDTO> getProductsByUserId(Long userId, Pageable pageable) {
        Page<Product> products = productRepository.findByUserId(userId, pageable);
        return products.map(productMapper::productToDto);
    }

    // Read Products by CategoryID
    public Page<ProductResponseDTO> getProductsByCategoryId(Long categoryId, Pageable pageable) {
        Page<Product> products = productRepository.findByCategoryId(categoryId, pageable);
        return products.map(productMapper::productToDto);
    }

    //UPDATE
    @Transactional//수정코드
    public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.PRODUCT_NOT_FOUND));
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
        if (!productRepository.existsById(id)) {
            throw new ServiceLogicException(ExceptionCode.PRODUCT_NOT_FOUND);
        }
        // 먼저 해당 Product를 참조하는 모든 OrderItem을 찾아서 삭제
        List<OrderItem> orderItems = orderItemRepository.findByProductId(id);
        orderItemRepository.deleteAll(orderItems);
        // 그 다음 Product 삭제
        productRepository.deleteById(id);
    }

    /*기존코드

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

     public List<Product> getProductsByUserId(Long userId) {
        return productRepository.findByUserId(userId);
    }

        @Transactional//기존코드
    public Product updateProduct(Long productId, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + productId));
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setStock(productRequestDTO.getStock());
        product.setDescription(productRequestDTO.getDescription());
        product.setImages(productRequestDTO.getImages());
        return productRepository.save(product);
    }
     */
    //READ

//    //Read All Products
//    public List<ProductResponseDTO> findAllProducts() {
//        List<Product> products = productRepository.findAll();
//        return products.stream()
//                .map(productMapper::productToDto)
//                .collect(Collectors.toList());
//    }
//
//
//    //Read Products By Productid
//    public ProductResponseDTO findProductById(Long id) {
//        Product product = productRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));
//        return productMapper.productToDto(product);
//    }
//
//
//    //Read Products by userID
//    public List<ProductResponseDTO> getProductsByUserId(Long userId) {
//        List<Product> products = productRepository.findByUserId(userId);
//        return products.stream()
//                .map(productMapper::productToDto)
//                .collect(Collectors.toList());
//    }
//
//    //Read Products by CategoryID
//    public List<ProductResponseDTO> getProductsByCategoryId(Long categoryId) {
//        List<Product> products = productRepository.findByCategoryId(categoryId);
//        List<ProductResponseDTO> productResponseDTOs = new ArrayList<>();
//        for (Product product : products) {
//            productResponseDTOs.add(productMapper.productToDto(product));
//        }
//        return productResponseDTOs;
//    }


}
