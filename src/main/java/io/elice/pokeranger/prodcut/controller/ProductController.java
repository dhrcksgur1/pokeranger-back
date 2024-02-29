package io.elice.pokeranger.prodcut.controller;

import io.elice.pokeranger.prodcut.entity.ProductRequestDTO;
import io.elice.pokeranger.prodcut.entity.ProductResponseDTO;
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

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //CREATE
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO createdProduct = productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(createdProduct);
    }

    //Read All Products
    @GetMapping//수정코드
    public ResponseEntity<List<ProductResponseDTO>> getProductList() {
        List<ProductResponseDTO> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //Read By ProductID
    @GetMapping("/{id}")//수정코드
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long id) {
        ProductResponseDTO product = productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    //Read By UserID
    @GetMapping("/user/{userId}")//수정코드
    public ResponseEntity<List<ProductResponseDTO>> getProductByUserId(@PathVariable Long userId) {
        List<ProductResponseDTO> products = productService.getProductsByUserId(userId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    //READ BY CategoryID
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponseDTO>> getProductByCategoryId(@PathVariable Long categoryId) {
        List<ProductResponseDTO> products = productService.getProductsByCategoryId(categoryId);
        return ResponseEntity.ok(products);
    }

    //UPADTE
    @PutMapping("/{id}") //수정코드
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO updatedProduct = productService.updateProduct(id, productRequestDTO);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //기존 코드들
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
