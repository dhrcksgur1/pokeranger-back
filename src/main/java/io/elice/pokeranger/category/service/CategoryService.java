package io.elice.pokeranger.category.service;

import io.elice.pokeranger.category.entity.Category;
import io.elice.pokeranger.category.entity.CategoryDTO;
import io.elice.pokeranger.category.mapper.CategoryMapper;
import io.elice.pokeranger.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }



    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        return optionalCategory.map(category -> {
            // Update user fields with values from userDTO
            category.setName(categoryDTO.getName());

            categoryRepository.save(category);
            return categoryMapper.categoryToCategoryDTO(category);
        }).orElse(null);
    }

    public List<CategoryDTO> getCategryAll() {
        return categoryRepository.findAll().stream().map(CategoryMapper.INSTANCE::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }


    // Other CRUD methods...

}