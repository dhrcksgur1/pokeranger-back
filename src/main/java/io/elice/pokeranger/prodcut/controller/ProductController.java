package io.elice.pokeranger.prodcut.controller;


import io.elice.pokeranger.prodcut.entity.Product;
import io.elice.pokeranger.prodcut.entity.ProductDto;
import io.elice.pokeranger.prodcut.mapper.ProductMapper;
import io.elice.pokeranger.prodcut.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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

    //Read
    @GetMapping
    public List<Product> product(){
        return productService.findAllProducts();
    }

    //Read By ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.findProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Create //엔티티로
//    @PostMapping
//    public Product createProduct(@RequestBody Product product){
//        return productService.save(product);
//    }

    @PostMapping // dto로
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto createdProduct = productService.createProduct(productDto);
        return ResponseEntity.ok(createdProduct);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productService.findProductById(id)
                .map(product -> {
                    product.setName(productDetails.getName());
                    product.setDescription(productDetails.getDescription());
                    product.setPrice(productDetails.getPrice());
                    product.setStock(productDetails.getStock());
                    product.setImages(productDetails.getImages());
                    Product updatedProduct = productService.save(product);
                    return ResponseEntity.ok(updatedProduct);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @PutMapping("/{id}") //Dto 수정과 생성이 동시에 진행됩니다
//    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productdto) {
//        return productService.findProductById(id)
//                .map(product -> {
//                    product.setName(productdto.getName());
//                    product.setDescription(productdto.getDescription());
//                    product.setPrice(productdto.getPrice());
//                    product.setStock(productdto.getStock());
//                    product.setImages(productdto.getImages());
//                    ProductDto updatedProduct = productService.createProduct(productdto);
//                    return ResponseEntity.ok(updatedProduct);
//                })
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

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
}
