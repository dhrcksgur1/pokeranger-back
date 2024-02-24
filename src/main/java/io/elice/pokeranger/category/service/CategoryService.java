package io.elice.pokeranger.category.service;

import io.elice.pokeranger.category.entity.Category;
import io.elice.pokeranger.category.entity.CategoryDTO;
import io.elice.pokeranger.category.mapper.CategoryMapper;
import io.elice.pokeranger.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category user = categoryMapper.categoryDTOToCategory(categoryDTO);

        categoryRepository.save(user);
        return categoryMapper.categoryToCategoryDTO(user);
    }

    public CategoryDTO getCategoryById(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        return optionalCategory.map(categoryMapper::categoryToCategoryDTO).orElse(null);
    }

    // Other CRUD methods...

}