package io.elice.pokeranger.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ExceptionCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 회원입니다."),
    Product_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 상품입니다."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 카테고리입니다."),
    CATEGORY_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 카테고리입니다."),
    //COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 댓글입니다.");

    private HttpStatus status;
    private String message;
}
