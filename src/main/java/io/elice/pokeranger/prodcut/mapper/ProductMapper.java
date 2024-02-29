package io.elice.pokeranger.prodcut.mapper;

import io.elice.pokeranger.prodcut.entity.Product;
import io.elice.pokeranger.prodcut.entity.ProductResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product dtoToProduct(ProductResponseDTO productResponseDto);
    ProductResponseDTO productToDto(Product product);
}