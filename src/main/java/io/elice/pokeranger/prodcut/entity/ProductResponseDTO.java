package io.elice.pokeranger.prodcut.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class ProductResponseDTO {
    private Long id; //postman 테스트 용 추후 삭제
    private String userName;
    private Long userId;
    private Long categoryId;
    private Long price;
    private String name;
    private String description;
    private Long stock;
    private String images;
}
