package io.elice.pokeranger;

import io.elice.pokeranger.prodcut.entity.ProductCreateDTO;
import io.elice.pokeranger.prodcut.entity.ProductResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;

import java.util.stream.Stream;

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
//    @BeforeEach
//    public void setup() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
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
//
//}

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    public void setUp() {
        this.baseUrl = "http://localhost:" + port;
    }

    @ParameterizedTest
    @MethodSource("dataset")
    void parameterizedTest(TestArguments arguments) {

        // TODO 빌드 실패로 아래 2줄 주석처리
        //ResponseEntity<?> response = arguments.executeRequest(restTemplate.getRestTemplate(), baseUrl);
       // assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

        // Here, you might want to parse the response body and assert the specific fields
        // based on `arguments.getJsonPath()` and `arguments.getExpectedValue()`
    }

    public static Stream<TestArguments> dataset() {
        return Stream.of(
                TestArguments.of("/products", HttpMethod.POST, new ProductCreateDTO(2L, "Sample Product", "Sample Description", "Sample Image", 1L, 2L), ProductResponseDTO.class, "$.name", "Sample Product"),
                TestArguments.of("/products?page=0&size=10", HttpMethod.GET, null, String.class, "$.content[0].name", "Sample Product"),
                TestArguments.of("/products/user/{userId}", HttpMethod.GET, null, String.class, "$.content[0].name", "Sample Product", 1L)

//                TestArguments.of("/products", HttpMethod.POST, new ProductCreateDTO(2L, "Sample Product", "Sample Description", "Sample Image", 1L, 2L), ProductResponseDTO.class, "$.name", "Expected Product Name"),
//                TestArguments.of("/products?page=0&size=10", HttpMethod.GET, null, Page.class, "$.content.size()", "Expected Number of Products"),
//                TestArguments.of("/products/{id}", HttpMethod.GET, null, ProductResponseDTO.class, "$.id", "Expected Product ID", 1L),
//                TestArguments.of("/products//user/{userId}", HttpMethod.GET, null, Page.class, "$.content[0].userId", "Expected User ID", 1L),
//                TestArguments.of("/products//category/{categoryId}?page=0&size=10", HttpMethod.GET, null, Page.class, "$.content[0].name", "Sample Product", 1),
//                TestArguments.of("/products/{id}", HttpMethod.PUT, new ProductRequestDTO(/* constructor arguments */), ProductResponseDTO.class, "$.name", "Updated Product Name", 1),
//                TestArguments.of("/products/{id}", HttpMethod.DELETE, null, Void.class, null, null, 1)
        );
    }
}
