package io.elice.pokeranger.prodcut.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDTO {

    @NotNull
    private Long userId;

    @NotNull(message = "Category ID 는 필수 입력란 입니다.")
    private Long categoryId;

    @NotBlank(message = "제품 명은 필수 입력란 입니다.")
    private String name;

    @NotNull(message = "상세설명은 필수 입력란 입니다.")
    private String description;

    @NotNull(message = "이미지는 필수 입력란 입니다.")
    private String images;

    @NotNull(message = "재고는 필수 입력란 입니다")
    @Min(value = 1, message = "1개 이상의 재고을 입력해 주세요.")
    private Long stock;

    @NotNull(message = "Price 는 필수 입력란 입니다")
    @Min(value = 0, message = "0이상의 가격을 입력해 주세요.")
    private Long price;

}
