package com.woodposters.controller;

import com.woodposters.beans.WizardState;
import com.woodposters.converters.CategoryConverter;
import com.woodposters.converters.ProductConverter;
import com.woodposters.entity.category.Category;
import com.woodposters.entity.product.Product;
import com.woodposters.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private WizardState wizardState;

    @GetMapping("getAllCategories")
    public ResponseEntity<List> getAllCategories() {
        List result = new ArrayList();
        Iterable<Category> categories = categoryRepository.findAll();
        categories.forEach(category -> result.add(CategoryConverter.convert(category, wizardState.getLocale())));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("getProductsByCategory/{id}")
    public ResponseEntity<List> getProductsByCategory(@PathVariable("id") Long id) {
        Category category = categoryRepository.findOne(id);
        Set<Product> products = category.getProducts();
        List result = ProductConverter.convert(products, wizardState.getLocale());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
