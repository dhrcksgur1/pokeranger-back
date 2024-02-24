package io.elice.pokeranger.prodcut.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long stock;

    @Column(nullable = false)
    private String description;

    //이미지 링크
    @Column
    private String images;

    public Product(String name, String description, Long price, Long stock) {
    }

    //생성일자 java.sql.SQLIntegrityConstraintViolationException: Column 'created_at' cannot be null발생합니다.
//    @CreatedDate
//    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
//    private LocalDateTime createdAt;
//
//    //수정일자
//    @LastModifiedDate
//    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP")
//    private LocalDateTime updatedAt;


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
