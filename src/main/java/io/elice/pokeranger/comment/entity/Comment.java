package io.elice.pokeranger.comment.entity;

import io.elice.pokeranger.prodcut.entity.Product;
import io.elice.pokeranger.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    public Comment(Product product, String content) {
        this.product =product;
        this.content = content;
    }

//    @CreatedDate
//    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
//    private LocalDateTime createdAt;
}
