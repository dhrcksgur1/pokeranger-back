package io.elice.pokeranger.prodcut.controller;


import io.elice.pokeranger.prodcut.entity.Product;
import io.elice.pokeranger.prodcut.entity.ProductRequestDTO;
import io.elice.pokeranger.prodcut.entity.ProductResponseDTO;
import io.elice.pokeranger.prodcut.mapper.ProductMapper;
import io.elice.pokeranger.prodcut.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService,ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    //CREATE
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO createdProduct = productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(createdProduct);
    }

    //Read All Products
    @GetMapping
    public List<Product> product(){
        return productService.findAllProducts();
    }

    //Read By ProductID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.findProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Read By UserID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Product>> getProductsByUserId(@PathVariable Long userId) {
        List<Product> products = productService.getProductsByUserId(userId);
        return ResponseEntity.ok(products);
    }


    //UPADTE
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productDto) {
        Product updatedProduct = productService.updateProduct(id, productDto);
        return ResponseEntity.ok(updatedProduct);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return productService.findProductById(id)
                .map(product -> {
                    productService.deleteProductById(id);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @DeleteMapping("/{id}") //version2
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        productService.deleteProduct(id);
//        return ResponseEntity.noContent().build();
//    }

    //Create //엔티티로
//    @PostMapping
//    public Product createProduct(@RequestBody Product product){
//        return productService.save(product);
//    }


    //    @PutMapping("/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
//        return productService.findProductById(id)
//                .map(product -> {
//                    product.setName(productDetails.getName());
//                    product.setDescription(productDetails.getDescription());
//                    product.setPrice(productDetails.getPrice());
//                    product.setStock(productDetails.getStock());
//                    product.setImages(productDetails.getImages());
//                    Product updatedProduct = productService.save(product);
//                    return ResponseEntity.ok(updatedProduct);
//                })
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    //    @PostMapping // dto로  완성코드
//    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductResponseDTO productDto) {
//        ProductResponseDTO createdProduct = productService.createProduct(productDto);
//        return ResponseEntity.ok(createdProduct);
//    }

    ////    UPDATE 오류코드
//    @PutMapping("/{id}") //Dto 수정과 생성이 동시에 진행됩니다
//    public ResponseEntity<ProductResponseDTO> editProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDto) {
//        return productService.findProductById(id)
//                .map(product -> {
//                    product.setName(productRequestDto.getName());
//                    product.setPrice(productRequestDto.getPrice());
//                    product.setStock(productRequestDto.getStock());
//                    product.setDescription(productRequestDto.getDescription());
//                    product.setImages(productRequestDto.getImages());
//                    ProductResponseDTO updatedProduct = productService.createProduct(productRequestDto);
//                    return ResponseEntity.ok(updatedProduct);
//                })
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

}
