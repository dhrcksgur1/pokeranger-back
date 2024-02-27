package io.elice.pokeranger.order.controller;

import io.elice.pokeranger.order.deliverystate.DeliveryStateRole;
import io.elice.pokeranger.order.entity.OrderRequestDTO;
import io.elice.pokeranger.order.entity.OrderResponseDTO;
import io.elice.pokeranger.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    // 결재창 페이지 - 유저의 정보 반환
//    @GetMapping
//    public ResponseEntity<UserResponseDTO> getOrder(){
//        return
//    }

    // 주문 생성
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        OrderResponseDTO orderResponseDTO = orderService.createOrder(orderRequestDTO);
        return new ResponseEntity<>(orderResponseDTO, HttpStatus.CREATED);
    }

    // 유저 주문내역 페이지 - 유저 아이디에 해당되는 주문 내역 반환
    @GetMapping("/orderlist/user")
    public ResponseEntity<List<OrderResponseDTO>> getOrderListForUser(@RequestParam Long userId){
        List<OrderResponseDTO> orderList = orderService.getOrderList(userId);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    // 유저 주문내역 페이지 주문 취소 기능
    @DeleteMapping("/orderlist/user/{orderId}")
    public ResponseEntity<Object> deleteOrderForUser(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 관리자 주문내역 페이지 - 모든 유저의 주문 내역 반환
    @GetMapping("/orderlist/admin")
    public ResponseEntity<List<OrderResponseDTO>> getOrderListForAdmin(@RequestParam Long userId){
        List<OrderResponseDTO> orderList = orderService.getOrderList(userId);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    // 관리자 주문내역 페이지 주문상태 변경 기능 - 주문의 id를 받아와 주문상태 변경
    @PutMapping("/orderlist/admin/state/{orderId}")
    public ResponseEntity<Object> changeOrderState(@RequestBody Map<String, String> body, @PathVariable Long orderId) {
        try {
            DeliveryStateRole state = DeliveryStateRole.valueOf(body.get("state"));
            orderService.updateOrderState(state, orderId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("잘못된 주문 상태 값입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    // 관리자 주문내역 페이지 주문 취소 기능
    @DeleteMapping("/orderlist/admin/{orderId}")
    public ResponseEntity<Object> deleteOrderForAdmin(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
