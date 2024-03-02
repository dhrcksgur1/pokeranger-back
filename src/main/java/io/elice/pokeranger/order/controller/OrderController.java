package io.elice.pokeranger.order.controller;

import io.elice.pokeranger.order.deliverystate.DeliveryStateRole;
import io.elice.pokeranger.order.entity.OrderRequestDTO;
import io.elice.pokeranger.order.entity.OrderResponseDTO;
import io.elice.pokeranger.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "주문 생성기능", description = "주문 생성기능입니다")
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        OrderResponseDTO orderResponseDTO = orderService.createOrder(orderRequestDTO);
        return new ResponseEntity<>(orderResponseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "유저 주문내역 조회", description = "유저아이디에 해당하는 주문내역을 가져옵니다")
    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrderListForUser(@PathVariable Long userId){
        List<OrderResponseDTO> orderList = orderService.getOrderList(userId);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @Operation(summary = "주문 취소기능", description = "배송준비중이면 주문을 취소할수있습니다")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Object> deleteOrder(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "관리자 주문내역 조회", description = "관리자 id가 확인되면 모든 유저의 주문내역을 가져옵니다")
    @GetMapping("/{userId}/admin")
    public ResponseEntity<List<OrderResponseDTO>> getOrderListForAdmin(@PathVariable Long userId){
        List<OrderResponseDTO> orderList = orderService.getOrderList(userId);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @Operation(summary = "주문상태 변경 기능", description = "관리자가 주문상태를 변경할수 있습니다.")
    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> updateOrderState(@RequestBody Map<String, String> body, @PathVariable Long orderId) {
        try {
            DeliveryStateRole state = DeliveryStateRole.valueOf(body.get("state"));
            OrderResponseDTO orderResponseDTO = orderService.updateOrderState(state, orderId);
            return new ResponseEntity<>(orderResponseDTO,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
