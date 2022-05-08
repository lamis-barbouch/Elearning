package tn.esprit.spring.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.CategoryRepo;
import tn.esprit.spring.entity.Category;


@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    /*---------------------------------------------------------*/
    public Category findCategory(String title) {
        return categoryRepo.findByTitle(title);
    }


    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }


    public Category getCategory(Long categoryId) {
        return categoryRepo.findById(categoryId).get();
    }


    public Set<Category> getAllCategory() {
        return new LinkedHashSet<>(categoryRepo.findAll());
    }


    public void deleteCategory(Long categoryId) {
        Category category = this.getCategory(categoryId);
        this.categoryRepo.delete(category);
    }


}