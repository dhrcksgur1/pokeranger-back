package io.elice.pokeranger.prodcut.entity;

import io.elice.pokeranger.category.entity.Category;
import io.elice.pokeranger.global.entity.BaseEntity;
import io.elice.pokeranger.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseEntity {
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

    //추가
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //추가
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    //생성일자 java.sql.SQLIntegrityConstraintViolationException: Column 'created_at' cannot be null발생합니다.
//    @CreatedDate
//    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
//    private LocalDateTime createdAt;
//
//    //수정일자
//    @LastModifiedDate
//    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP")
//    private LocalDateTime updatedAt;

    //추가
    public Product(User user,Category category ,String name, Long price,Long stock,String description,String images){
        this.user = user;
        this.category = category;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this. images = images;
    }

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

    public void setUser(User user) {
        this.user =user;
    }
}
