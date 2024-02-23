package io.elice.pokeranger.order.controller;

import io.elice.pokeranger.order.entity.OrderRequestDTO;
import io.elice.pokeranger.order.entity.OrderResponseDTO;
import io.elice.pokeranger.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;


    // 주문 생성
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrders(@RequestBody OrderRequestDTO orderRequestDTO){
        OrderResponseDTO orderResponseDTO = orderService.createOrder(orderRequestDTO);
        return new ResponseEntity<>(orderResponseDTO, HttpStatus.CREATED);
    }
    // 유저 주문조회 페이지
    // 유저 주문조회 페이지 주문 취소
    // 관리자 주문조회 페이지
    // 관리자 주문상태 변경
    //



}
