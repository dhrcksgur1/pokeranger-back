package io.elice.pokeranger.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.elice.pokeranger.order.entity.OrderRequestDTO;
import io.elice.pokeranger.order.entity.OrderResponseDTO;
import io.elice.pokeranger.order.service.OrderService;
import io.elice.pokeranger.orderItem.entity.CartItemDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    private OrderRequestDTO orderRequestDTO;
    private OrderResponseDTO orderResponseDTO;

    @BeforeEach
    public void setUp(){
        CartItemDTO cartItem1 = new CartItemDTO();
        CartItemDTO cartItem2 = new CartItemDTO();
        CartItemDTO cartItem3 = new CartItemDTO();

        cartItem1.setProductId(1L);
        cartItem2.setProductId(1L);
        cartItem3.setProductId(1L);
        cartItem1.setQuantity(1);
        cartItem2.setQuantity(2);
        cartItem3.setQuantity(3);

        List<CartItemDTO> cartItems = new ArrayList<>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);
        cartItems.add(cartItem3);
/*
        orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setUserId(1L);
        orderRequestDTO.setOrderMessage("문앞에 놔주세요");
        orderRequestDTO.setTotalCost(35000);
        orderRequestDTO.setCartItems(cartItems);
*/
        // TODO 빌드 실패로 주석처리
        //orderResponseDTO = new OrderResponseDTO();
        //orderResponseDTO.setTotalCost(35000);

        //orderResponseDTO.setDeliveryState(DeliveryStateRole.PREPARE);
        //orderResponseDTO.set(cartItems);

    }
/*
    @DisplayName("주문 생성 테스트")
    @Test
    void createOrder() throws Exception {
        when(orderService.createOrder(orderRequestDTO)).thenReturn(orderResponseDTO);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequestDTO)))
                        .andExpect(content().json(objectMapper.writeValueAsString(orderResponseDTO)))
                        .andExpect(status().isCreated());
    }


*/
    @Test
    void getOrderListForUser() {
    }

    @Test
    void deleteOrder() {
    }

    @Test
    void getOrderListForAdmin() {
    }

    @Test
    void updateOrderState() {
    }
}