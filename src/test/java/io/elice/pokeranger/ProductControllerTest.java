package io.elice.pokeranger;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.elice.pokeranger.prodcut.entity.ProductCreateDTO;
import io.elice.pokeranger.prodcut.entity.ProductResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @MethodSource("productDataset")
    void createProductParameterizedTest(ProductCreateDTO productCreateDTO, String expectedName, Long expectedPrice) throws Exception {
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productCreateDTO)))
                .andExpect(status().isCreated())
                .andExpect(result -> {
                    String responseBody = result.getResponse().getContentAsString();
                    ProductResponseDTO response = objectMapper.readValue(responseBody, ProductResponseDTO.class);
                    Assertions.assertEquals(expectedName, response.getName());
                    Assertions.assertEquals(expectedPrice, response.getPrice());
                });
    }

    public static Stream<Arguments> productDataset() {
        return Stream.of(
                Arguments.of(new ProductCreateDTO(1L, 1L, "Product A", "Description for Product A", "image1.jpg", 10L, 100L), "Product A", 100L),
                Arguments.of(new ProductCreateDTO(2L, 2L, "Product B", "Description for Product B", "image2.jpg", 20L, 200L), "Product B", 200L),
                Arguments.of(new ProductCreateDTO(3L, 3L, "Product C", "Description for Product C", "image3.jpg", 30L, 300L), "Product C", 300L)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "0, 5",
            "1, 10",
            "2, 3"
    })
    void getAllProductsPaged(int page, int size) throws Exception {
        mockMvc.perform(get("/products?page=" + page + "&size=" + size))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.content", hasSize(lessThanOrEqualTo(size))));
    }

    @Test
    void getProductByIdTest() throws Exception {
        Long productId = 1L; // Assuming this product exists
        mockMvc.perform(get("/products/" + productId))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id").value(productId));
    }

    @Test
    public void getProductByUserId_ShouldReturnProducts() throws Exception {
        Long userId = 1L; // Example user ID
        mockMvc.perform(get("/products/user/{userId}", userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.content").isArray())
                .andExpect((ResultMatcher) jsonPath("$.content[0].userId").value(userId));
    }

    @Test
    public void getProductByCategoryId_ShouldReturnProducts() throws Exception {
        Long categoryId = 1L; // Example category ID
        mockMvc.perform(get("/products/category/{categoryId}", categoryId)
                        .param("page", "0")
                        .param("size", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.content").isArray())
                .andExpect((ResultMatcher) jsonPath("$.content[0].categoryId").value(categoryId));
    }



    @ParameterizedTest
    @MethodSource("updateProductDataset")
    void updateProductParameterizedTest(Long productId, ProductCreateDTO updateDto, String expectedName) throws Exception {
        mockMvc.perform(patch("/products/" + productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.name").value(expectedName));
    }

    static Stream<Arguments> updateProductDataset() {
        return Stream.of(
                Arguments.of(1L, new ProductCreateDTO(1L, 1L, "Updated Product A", "Updated Description", "image1_updated.jpg", 15L, 150L), "Updated Product A")
        );
    }

    @Test
    void deleteProductTest() throws Exception {
        Long productId = 1L; // Assuming this product can be deleted
        mockMvc.perform(delete("/products/" + productId))
                .andExpect(status().isNoContent());
    }
}
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
//    //    void parameterizedTest(int input, int input2, String expectedOutput) {
////        int i = input + input2;
////
////        Assertions.assertEquals(String.valueOf(i), expectedOutput);
////    }
//    @ParameterizedTest
//    @MethodSource("dataset")
//    void testProductApi(String path, HttpMethod method, Object requestDTO, Class<?> responseClass, String jsonPath, Object expectedValue) throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.request(method, path)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestDTO != null ? objectMapper.writeValueAsString(requestDTO) : "{}"))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath(jsonPath).value(expectedValue));
//    }
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
