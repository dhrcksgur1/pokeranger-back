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
//    @GetMapping//기존코드
//    public List<Product> getProductList(){
//        return productService.findAllProducts();
//    }
    @GetMapping//수정코드
    public ResponseEntity<List<ProductResponseDTO>> getProductList() {
        List<ProductResponseDTO> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //Read By ProductID
//    @GetMapping("/{id}")//기존코드
//    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
//        return productService.findProductById(id)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @GetMapping("/{id}")//수정코드
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long id) {
        ProductResponseDTO product = productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    //Read By UserID
//    @GetMapping("/user/{userId}")//기존코드
//    public ResponseEntity<List<Product>> getProductsByUserId(@PathVariable Long userId) {
//        List<Product> products = productService.getProductsByUserId(userId);
//        return ResponseEntity.ok(products);
//    }

    @GetMapping("/user/{userId}")//수정코드
    public ResponseEntity<List<ProductResponseDTO>> getProductsByUserId(@PathVariable Long userId) {
        List<ProductResponseDTO> products = productService.getProductsByUserId(userId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    //READ BY CategoryID
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategoryId(@PathVariable Long categoryId) {
        List<ProductResponseDTO> products = productService.getProductsByCategoryId(categoryId);
        return ResponseEntity.ok(products);
    }

    //UPADTE
//    @PutMapping("/{id}") //기존코드
//    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productDto) {
//        Product updatedProduct = productService.updateProduct(id, productDto);
//        return ResponseEntity.ok(updatedProduct);
//    }

    @PutMapping("/{id}") //수정코드
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO updatedProduct = productService.updateProduct(id, productRequestDTO);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

//    @PutMapping("/{id}") //repsonsebody로 받도록?
//    public @ResponseBody ProductResponseDTO updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO) {
//        return productService.updateProduct(id, productRequestDTO);
//    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

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
