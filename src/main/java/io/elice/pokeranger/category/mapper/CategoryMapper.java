package io.elice.pokeranger.category.mapper;

import io.elice.pokeranger.category.entity.Category;
import io.elice.pokeranger.category.entity.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "createdAt", ignore = true)
    Category categoryDTOToCategory(CategoryDTO categoryDTO);

    CategoryDTO categoryToCategoryDTO(Category user);
}