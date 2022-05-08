package tn.esprit.spring.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Category;
import tn.esprit.spring.entity.ResponseDto;
import tn.esprit.spring.service.CategoryService;



@RestController
@RequestMapping("/category")
@CrossOrigin

public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping("/")
    public ResponseEntity<?> addCategory(@RequestBody Category categoryDto) {
        if (this.categoryService.findCategory(categoryDto.getTitle()) == null) {
            Category category = new Category();
            BeanUtils.copyProperties(categoryDto, category);
            this.categoryService.saveCategory(category);
            return ResponseEntity.ok(new ResponseDto("Save"));
        } else {
            return ResponseEntity.ok(new ResponseDto("Already Exist"));
        }
    }

    /*----------------------------------------------------------------------*/
    @GetMapping("/all-category")
    public ResponseEntity<?> getAllCategory() {
        Set<Category> categoryDtoSet = this.getCategoryDtoSet(this.categoryService.getAllCategory());
        return ResponseEntity.ok(categoryDtoSet);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getAllCategory(@PathVariable("categoryId") Long categoryId) {
        Category categoryDto = new Category();
        Category category = this.categoryService.getCategory(categoryId);
        BeanUtils.copyProperties(category, categoryDto);
        return ResponseEntity.ok(categoryDto);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestBody Category categoryDto) {
        if (this.categoryService.findCategory(categoryDto.getTitle()) == null) {
            Category category = new Category();
            BeanUtils.copyProperties(categoryDto, category);
            this.categoryService.saveCategory(category);
            return ResponseEntity.ok(new ResponseDto("Update"));
        } else {
            return ResponseEntity.ok(new ResponseDto("Title Already Used"));
        }
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(new ResponseDto("Delete"));
    }



    /*------------------------HELPER METHODS-----------------------*/

    private Set<Category> getCategoryDtoSet(Set<Category> categorySet) {
        Set<Category> categoryDtoSet = new LinkedHashSet<>();
        for (Category category : categorySet) {
            Category categoryDto = new Category();
            BeanUtils.copyProperties(category, categoryDto);
            categoryDtoSet.add(categoryDto);
        }
        return categoryDtoSet;
    }

}
