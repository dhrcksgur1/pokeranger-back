package io.elice.pokeranger.category.mapper;

import io.elice.pokeranger.category.entity.Category;
import io.elice.pokeranger.category.entity.CategoryDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-23T20:52:07+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category categoryDTOToCategory(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setName( categoryDTO.getName() );

        return category;
    }

    @Override
    public CategoryDTO categoryToCategoryDTO(Category user) {
        if ( user == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setName( user.getName() );

        return categoryDTO;
    }
}
