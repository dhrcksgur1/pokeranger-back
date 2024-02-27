package io.elice.pokeranger.prodcut.entity;

import io.elice.pokeranger.orderItem.entity.CartItemDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class ProductRequestDTO {
    private Long userId;
    private String name;
    private Long price;
    private Long stock;
    private String description;
    private String images;
}
