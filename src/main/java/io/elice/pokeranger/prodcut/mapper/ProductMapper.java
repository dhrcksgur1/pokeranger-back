package io.elice.pokeranger.prodcut.mapper;

import io.elice.pokeranger.prodcut.entity.Product;
import io.elice.pokeranger.prodcut.entity.ProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product dtoToProduct(ProductResponseDTO productResponseDto);

//    @Mapping(target = "userId", expression = "java(mapUserToUserID(productResponseDto.getUserDTO()))")
    ProductResponseDTO productToDto(Product product);
}