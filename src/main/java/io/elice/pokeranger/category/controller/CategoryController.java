package io.elice.pokeranger.category.controller;

import io.elice.pokeranger.category.entity.CategoryDTO;
import io.elice.pokeranger.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController( CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }


    @PostMapping
    public ResponseEntity<CategoryDTO> createUser(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO createdUser = categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long categoryId) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(categoryDTO);
    }

    // Other CRUD endpoints...

}