package io.elice.pokeranger.category.controller;

import io.elice.pokeranger.category.entity.CategoryDTO;
import io.elice.pokeranger.category.service.CategoryService;
import io.elice.pokeranger.user.entity.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


/*
    [ 카테고리 추가 페이지 ] -> 카테고리 추가   // category 도메인
    요청 타입 : post
    endPoint :
    @ResponseBody  name
            (함수명) : createCategory
    반환값 : ResponseEntity<CateogoryDTO>
*/
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO createCategory = categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok(createCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long categoryId) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(categoryDTO);
    }


    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateUserRole(@PathVariable(name = "categoryId") Long categoryId, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updateCategory = categoryService.updateCategory(categoryId, categoryDTO);
        return ResponseEntity.ok(updateCategory);
    }




    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable(name = "categoryId") Long categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete category with ID " + categoryId);
        }
    }


}