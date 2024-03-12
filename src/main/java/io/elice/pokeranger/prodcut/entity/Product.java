package io.elice.pokeranger.prodcut.entity;

import io.elice.pokeranger.category.entity.Category;
import io.elice.pokeranger.global.entity.BaseEntity;
import io.elice.pokeranger.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
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

    @Column
    private String images;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false  )
    private Category category;

    public Product(User user, Category category ,String name, Long price,Long stock,String description,String images){
        this.user = user;
        this.category = category;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.images = images;
    }

}
