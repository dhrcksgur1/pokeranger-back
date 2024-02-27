package io.elice.pokeranger.comment.controller;

import io.elice.pokeranger.comment.entity.Comment;
import io.elice.pokeranger.comment.entity.CommentResponseDTO;
import io.elice.pokeranger.comment.entity.CommentRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
//@AllArgsConstructor
//@RequestMapping("/comments")
//public class CommentController {
//    private final CommentService commentService;
//
//    @PostMapping
//    public ResponseEntity<Comment> createComment(@RequestBody CommentRequestDTO commentRequestDto) {
//        Comment comment = commentService.createComment(
//                commentRequestDto.getUserId(), commentRequestDto.getProductId(), new Comment(commentRequestDto.getContent()));
//        return ResponseEntity.ok(comment);
//    }
//
//
//}
