package io.elice.pokeranger.category.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Data
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @CreatedDate
    private Date createdAt;

    private Date deletedAt;

    public Category(String name)  {
        this.name = name;
    }
}
