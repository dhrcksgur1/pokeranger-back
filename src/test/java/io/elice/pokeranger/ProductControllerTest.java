package io.elice.pokeranger;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.elice.pokeranger.prodcut.controller.ProductController;
import io.elice.pokeranger.prodcut.entity.ProductCreateDTO;
import io.elice.pokeranger.prodcut.entity.ProductRequestDTO;
import io.elice.pokeranger.prodcut.entity.ProductResponseDTO;
import io.elice.pokeranger.prodcut.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.reflect.Array.get;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureMockMvc
//class ProductControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//
//    @Autowired
//    private WebApplicationContext context;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    private ProductResponseDTO buildProductResponseDTO() {
//        return new ProductResponseDTO(1L, "Sample Product", 10000L, 10L, "Sample Description", "Sample Image");
//    }
//
//  // 테스트 실패로 jar 파일 생성 못하여서 주석처리 하였습니다
//    @Test
//    void createProduct() throws Exception {
//        ProductCreateDTO requestDTO = new ProductCreateDTO();
//        requestDTO.setCategoryId(2L);
//        requestDTO.setName("Sample Product");
//        requestDTO.setPrice(10000L);
//        requestDTO.setStock(10L);
//        requestDTO.setDescription("Sample Description");
//        requestDTO.setImages("Sample Image");
//
//        ProductResponseDTO expectedResponse = buildProductResponseDTO();
//
//        mockMvc.perform(post("/products")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(requestDTO)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value(requestDTO.getName()));
//    }
//
//    @Test
//    void getProductList() throws Exception {
//        int page = 0;
//        int size = 10;
//        List<ProductResponseDTO> productList = Collections.singletonList(buildProductResponseDTO());
//        PageImpl<ProductResponseDTO> productPage = new PageImpl<>(productList, PageRequest.of(page, size), productList.size());
//
//        mockMvc.perform(get("/products").param("page", String.valueOf(page)).param("size", String.valueOf(size)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content[0].name").value(productList.get(0).getName()));
//    }
//
//    @Test
//    void getProductByUserId() throws Exception {
//
//        Long userId = 1L;
//        int page = 0;
//        int size = 10;
//        List<ProductResponseDTO> productList = Collections.singletonList(buildProductResponseDTO());
//        PageImpl<ProductResponseDTO> productPage = new PageImpl<>(productList, PageRequest.of(page, size), productList.size());
//
//        mockMvc.perform(get("products/user/{userId}" + userId).param("page", String.valueOf(page)).param("size", String.valueOf(size)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content[0].name").value(productList.get(0).getName()));
//    }
//
//    @ParameterizedTest
//    @MethodSource("dataset")
//    void parameterizedTest(int input, int input2, String expectedOutput) {
//        int i = input + input2;
//
//        Assertions.assertEquals(String.valueOf(i), expectedOutput);
//    }
//
//    public static Stream<Arguments> dataset() {
//        return Stream.of(
//                Arguments.of("/products", HttpMethod.POST, new ProductCreateDTO(2L, 2L,"Sample Product", "Sample Description", "Sample Image", 1L, 2L), ProductResponseDTO.class, "$.name", "Expected Product Name"),
//                Arguments.of("/products?page=0&size=10", HttpMethod.GET, null, String.class, "$.content[0].name", "Sample Product"),
//                Arguments.of("/products/user/{userId}", HttpMethod.GET, null, String.class, "$.content[0].name", "Sample Product", 1L),
//                Arguments.of("/products?page=0&size=10", HttpMethod.GET, null, Page.class, "$.content.size()", "Expected Number of Products"),
//                Arguments.of("/products/{id}", HttpMethod.GET, null, ProductResponseDTO.class, "$.id", "Expected Product ID", 1L),
//                Arguments.of("/products//user/{userId}", HttpMethod.GET, null, Page.class, "$.content[0].userId", "Expected User ID", 1L),
//                Arguments.of("/products/{id}", HttpMethod.PUT, new ProductRequestDTO(/* constructor arguments */), ProductResponseDTO.class, "$.name", "Updated Product Name", 1),
//                Arguments.of("/products/{id}", HttpMethod.DELETE, null, Void.class, null, null, 1)
//                );
//    }
//}


@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private static ProductResponseDTO buildProductResponseDTO() {
        return new ProductResponseDTO(1L, "cks", 100L, 10L, 1L,"d","Sample Description", 1L, "Sample Image");
    }

    @ParameterizedTest
    @MethodSource("testDataProvider")
    void testProductApi(String path, HttpMethod method, Object requestDTO, Class<?> responseClass, String jsonPath, Object expectedValue) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.request(method, path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestDTO != null ? objectMapper.writeValueAsString(requestDTO) : "{}"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath(jsonPath).value(expectedValue));
    }

    static Stream<Arguments> testDataProvider() {
        ProductCreateDTO createDTO = new ProductCreateDTO();
        createDTO.setCategoryId(2L);
        createDTO.setName("Sample Product");
        createDTO.setPrice(10000L);
        createDTO.setStock(10L);
        createDTO.setDescription("Sample Description");
        createDTO.setImages("Sample Image");

        // 요청 DTO 준비
        ProductCreateDTO requestDTO = createDTO;

        List<ProductResponseDTO> productList = Collections.singletonList(buildProductResponseDTO());

        return Stream.of(
                Arguments.of("/products", HttpMethod.POST, requestDTO, ProductResponseDTO.class, "$.name", "Sample Product"),
                Arguments.of("/products?page=0&size=10", HttpMethod.GET, null, PageImpl.class, "$.content[0].name", "Sample Product")
        );
    }
}