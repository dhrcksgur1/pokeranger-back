package io.elice.pokeranger.comment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentRequestDTO {
    private Long productId;
    private Long userId;
    private String content;
}
