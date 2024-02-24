package io.elice.pokeranger.prodcut.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    private String name;
    private Long price;
    private Long stock;
    private String Description;
    private String images;
}
