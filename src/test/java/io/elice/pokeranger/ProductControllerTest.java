package io.elice.pokeranger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.elice.pokeranger.prodcut.entity.ProductRequestDTO;
import io.elice.pokeranger.prodcut.entity.ProductResponseDTO;
import io.elice.pokeranger.prodcut.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;
import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // MockBean으로 교체
    private ProductService productService;

    @Autowired
    private WebApplicationContext context;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private ProductResponseDTO buildProductResponseDTO() {
        return new ProductResponseDTO(1L, "Sample Product", 10000L, 10L, "Sample Description", "Sample Image", LocalDateTime.now(), LocalDateTime.now());
    }

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void createProduct() throws Exception {
        ProductRequestDTO requestDTO = new ProductRequestDTO();
        requestDTO.setUserId(1L);
        requestDTO.setCategoryId(2L);
        requestDTO.setName("Sample Product");
        requestDTO.setPrice(10000L);
        requestDTO.setStock(10L);
        requestDTO.setDescription("Sample Description");
        requestDTO.setImages("Sample Image");

        ProductResponseDTO expectedResponse = buildProductResponseDTO();

        given(productService.createProduct(any())).willReturn(expectedResponse);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(requestDTO.getName()));
    }

    @Test
    void getProductList() throws Exception {
        int page = 0;
        int size = 10;
        List<ProductResponseDTO> productList = Collections.singletonList(buildProductResponseDTO());
        PageImpl<ProductResponseDTO> productPage = new PageImpl<>(productList, PageRequest.of(page, size), productList.size());

        given(productService.findAllProducts(any())).willReturn(productPage);

        mockMvc.perform(get("/products").param("page", String.valueOf(page)).param("size", String.valueOf(size)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value(productList.get(0).getName()));
    }

    @Test
    void getProductByUserId() throws Exception {
        Long userId = 1L;
        int page = 0;
        int size = 10;
        List<ProductResponseDTO> productList = Collections.singletonList(buildProductResponseDTO());
        PageImpl<ProductResponseDTO> productPage = new PageImpl<>(productList, PageRequest.of(page, size), productList.size());

        given(productService.getProductsByUserId(eq(userId), any())).willReturn(productPage);

        mockMvc.perform(get("products/user/{userId}" + userId).param("page", String.valueOf(page)).param("size", String.valueOf(size)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value(productList.get(0).getName()));
    }
}

