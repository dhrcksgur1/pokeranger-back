package io.elice.pokeranger.prodcut.mapper;

import io.elice.pokeranger.prodcut.entity.Product;
import io.elice.pokeranger.prodcut.entity.ProductDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-27T20:58:13+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product dtoToProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( productDto.getName() );
        product.price( productDto.getPrice() );
        product.stock( productDto.getStock() );
        product.description( productDto.getDescription() );
        product.images( productDto.getImages() );

        return product.build();
    }

    @Override
    public ProductDto productToDto(Product product) {
        if ( product == null ) {
            return null;
        }

        String name = null;
        Long price = null;
        Long stock = null;
        String images = null;

        name = product.getName();
        price = product.getPrice();
        stock = product.getStock();
        images = product.getImages();

        String description = null;

        ProductDto productDto = new ProductDto( name, price, stock, description, images );

        productDto.setDescription( product.getDescription() );

        return productDto;
    }
}
