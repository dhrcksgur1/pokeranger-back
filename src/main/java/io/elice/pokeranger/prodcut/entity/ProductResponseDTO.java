package io.elice.pokeranger.prodcut.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private Long userId;
    private String name;
    private Long price;
    private Long stock;
    private String description;
    private String images;
}
