package io.elice.pokeranger.prodcut.entity;

import io.elice.pokeranger.orderItem.entity.CartItemDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class ProductRequestDTO {
    @NotNull(message = "User ID 는 필수 입력란 입니다.")
    private Long userId;

    @NotNull(message = "Category ID 는 필수 입력란 입니다.")
    private Long categoryId;

    @NotBlank(message = "제품 명은 필수 입력란 입니다.")
    private String name;

    @NotNull(message = "Price 는 필수 입력란 입니다")
    @Min(value = 0, message = "0이상의 가격을 입력해 주세요.")
    private Long price;

    @NotNull(message = "재고는 필수 입력란 입니다")
    @Min(value = 1, message = "1개 이상의 재고을 입력해 주세요.")
    private Long stock;

    @NotNull(message = "상세설명은 필수 입력란 입니다.")
    private String description;

    private String images;

    public ProductRequestDTO() {

    }
}

