package io.elice.pokeranger.prodcut.mapper;

import io.elice.pokeranger.prodcut.entity.Product;
import io.elice.pokeranger.prodcut.entity.ProductResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-12T23:47:38+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product dtoToProduct(ProductResponseDTO productResponseDto) {
        if ( productResponseDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( productResponseDto.getId() );
        product.name( productResponseDto.getName() );
        product.price( productResponseDto.getPrice() );
        product.stock( productResponseDto.getStock() );
        product.description( productResponseDto.getDescription() );
        product.images( productResponseDto.getImages() );

        return product.build();
    }

    @Override
    public ProductResponseDTO productToDto(Product product) {
        if ( product == null ) {
            return null;
        }

        Long id = null;
        Long price = null;
        String name = null;
        String description = null;
        Long stock = null;
        String images = null;

        id = product.getId();
        price = product.getPrice();
        name = product.getName();
        description = product.getDescription();
        stock = product.getStock();
        images = product.getImages();

        String userName = null;
        Long categoryId = null;

        ProductResponseDTO productResponseDTO = new ProductResponseDTO( id, userName, categoryId, price, name, description, stock, images );

        return productResponseDTO;
    }
}
