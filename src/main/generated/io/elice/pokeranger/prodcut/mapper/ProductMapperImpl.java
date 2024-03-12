package io.elice.pokeranger.prodcut.mapper;

import io.elice.pokeranger.prodcut.entity.Product;
import io.elice.pokeranger.prodcut.entity.ProductResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-12T23:20:27+0900",
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
        String name = null;
        Long price = null;
        Long stock = null;
        String description = null;
        String images = null;

        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        stock = product.getStock();
        description = product.getDescription();
        images = product.getImages();

        ProductResponseDTO productResponseDTO = new ProductResponseDTO( id, name, price, stock, description, images );

        return productResponseDTO;
    }
}
