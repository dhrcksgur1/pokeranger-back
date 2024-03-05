package io.elice.pokeranger.category.controller;

import io.elice.pokeranger.category.entity.Category;
import io.elice.pokeranger.category.entity.CategoryDTO;
import io.elice.pokeranger.category.service.CategoryService;
import io.elice.pokeranger.user.entity.User;
import io.elice.pokeranger.user.entity.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController( CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }


    @Operation(summary = "카테고리 생성 ", description = "신규 카테고리 추가 ")
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO createCategory = categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok(createCategory);
    }

    @Operation(summary = "카테고리 요청 ", description = "id 에 해당하는 카테고리 반환 ")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable(name = "id") Long categoryId) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(categoryDTO);
    }

    @CrossOrigin(origins = "http://kdt-cloud-1-team03.elicecoding.com")
    @Operation(summary = "카테고리 전체 요청  ", description = "요청 ")
    @GetMapping("")
    public ResponseEntity<List<Category>> getCategory() {
        List<Category> categoryDTOs = categoryService.getCategryAll();

        return new ResponseEntity<>(categoryDTOs, HttpStatus.OK);

    }


    @Operation(summary = "카테고리 수정 ", description = "id 에 해당하는 카테고리 수정 ")
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateUserRole(@PathVariable(name = "categoryId") Long categoryId, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updateCategory = categoryService.updateCategory(categoryId, categoryDTO);
        return ResponseEntity.ok(updateCategory);
    }



    @Operation(summary = "카테고리 삭제 ", description = "id 에 해당하는 카테고리 제거 ")
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