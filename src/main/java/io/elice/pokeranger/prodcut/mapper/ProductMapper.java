package io.elice.pokeranger.prodcut.mapper;

import io.elice.pokeranger.prodcut.entity.Product;
import io.elice.pokeranger.prodcut.entity.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "updatedAt", ignore = true)
    Product dtoToProduct(ProductDto productDto);
    ProductDto productToDto(Product product);
}