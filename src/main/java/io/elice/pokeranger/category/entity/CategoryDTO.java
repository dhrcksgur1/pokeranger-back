package io.elice.pokeranger.category.entity;
import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    private String title;
    private String description;
    private String themeClass;
    private String imageKey;
}
