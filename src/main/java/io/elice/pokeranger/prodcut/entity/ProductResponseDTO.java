package io.elice.pokeranger.prodcut.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponseDTO {
//    private Long id; //postman 테스트 용 추후 삭제
    private String name;
    private Long price;
    private Long stock;
    private String description;
    private String images;
}
