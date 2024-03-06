package io.elice.pokeranger.prodcut.controller;

import io.elice.pokeranger.prodcut.entity.ProductRequestDTO;
import io.elice.pokeranger.prodcut.entity.ProductResponseDTO;
import io.elice.pokeranger.prodcut.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //CREATE
    @Operation(summary = "물품 등록기능", description = "물품 등록")
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Validated @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO createdProduct = productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(createdProduct);
    }

    // Read All Products
    @Operation(summary = "물품 조회 기능", description = "전체 물품 조회")
    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getProductList(@PageableDefault(size = 10) Pageable pageable) {
        Page<ProductResponseDTO> products = productService.findAllProducts(pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //Read By ProductID
    @Operation(summary = "물품 조회 기능", description = "물품 고유 id로 검색")
    @GetMapping("/{id}")//수정코드
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long id) {
        ProductResponseDTO product = productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // Read By UserID
    @Operation(summary = "물품 조회 기능", description = "유저 고유 id로 검색")
    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<ProductResponseDTO>> getProductByUserId(@PathVariable Long userId, @PageableDefault(size = 10) Pageable pageable) {
        Page<ProductResponseDTO> products = productService.getProductsByUserId(userId, pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // READ BY CategoryID
    @Operation(summary = "물품 조회 기능", description = "카테고리 고유 id로 검색")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<ProductResponseDTO>> getProductByCategoryId(@PathVariable Long categoryId, @PageableDefault(size = 10) Pageable pageable) {
        Page<ProductResponseDTO> products = productService.getProductsByCategoryId(categoryId, pageable);
        return ResponseEntity.ok(products);
    }

    //UPADTE
    @Operation(summary = "등록 물품 수정 기능", description = "등록된 물품 정보 수정")
    @PutMapping("/{id}") //수정코드
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id,@Validated @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO updatedProduct = productService.updateProduct(id, productRequestDTO);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    //Delete
    @Operation(summary = "등록 물품 삭제 기능", description = "등록된 물품을 물품고유id로 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //기존 코드들

    //    //Read All Products
//    @Operation(summary = "물품 조회 기능", description = "전체 물품 조회")
//    @GetMapping//수정코드
//    public ResponseEntity<List<ProductResponseDTO>> getProductList() {
//        List<ProductResponseDTO> products = productService.findAllProducts();
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }
//

//
//
//    //Read By UserID
//    @Operation(summary = "물품 조회 기능", description = "유저 고유 id로 검색")
//    @GetMapping("/user/{userId}")//수정코드
//    public ResponseEntity<List<ProductResponseDTO>> getProductByUserId(@PathVariable Long userId) {
//        List<ProductResponseDTO> products = productService.getProductsByUserId(userId);
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }
//
//
//    //READ BY CategoryID
//    @Operation(summary = "물품 조회 기능", description = "카테고리 고유 id로 검색")
//    @GetMapping("/category/{categoryId}")
//    public ResponseEntity<List<ProductResponseDTO>> getProductByCategoryId(@PathVariable Long categoryId) {
//        List<ProductResponseDTO> products = productService.getProductsByCategoryId(categoryId);
//        return ResponseEntity.ok(products);
//    }

    //    @GetMapping//기존코드
//    public List<Product> getProductList(){
//        return productService.findAllProducts();
//    }


    //    @GetMapping("/{id}")//기존코드
//    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
//        return productService.findProductById(id)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    //    @GetMapping("/user/{userId}")//기존코드
//    public ResponseEntity<List<Product>> getProductsByUserId(@PathVariable Long userId) {
//        List<Product> products = productService.getProductsByUserId(userId);
//        return ResponseEntity.ok(products);
//    }

    //    @PutMapping("/{id}") //기존코드
//    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productDto) {
//        Product updatedProduct = productService.updateProduct(id, productDto);
//        return ResponseEntity.ok(updatedProduct);
//    }

    //    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
//        return productService.findProductById(id)
//                .map(product -> {
//                    productService.deleteProductById(id);
//                    return ResponseEntity.ok().build();
//                })
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

}
