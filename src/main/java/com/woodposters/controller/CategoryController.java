package com.woodposters.controller;

import com.woodposters.beans.Locale;
import com.woodposters.beans.WizardState;
import com.woodposters.converters.CategoryConverter;
import com.woodposters.converters.CommonConverter;
import com.woodposters.converters.ProductConverter;
import com.woodposters.entity.adminModel.AdminBaseNameObject;
import com.woodposters.entity.adminModel.AdminProductType;
import com.woodposters.entity.category.Category;
import com.woodposters.entity.material.Material;
import com.woodposters.entity.product.Product;
import com.woodposters.repository.CategoryRepository;
import com.woodposters.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private WizardState wizardState;

    @GetMapping("getAllCategories")
    public ResponseEntity<List> getAllCategories() {
        List result = new ArrayList();
        Iterable<Category> categories = categoryService.getAllCategories();
        categories.forEach(category -> result.add(CategoryConverter.convert(category, wizardState.getLocale())));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("getProductsByCategory/{id}")
    public ResponseEntity<List> getProductsByCategory(@PathVariable("id") Long id) {
        Category category = categoryService.findCategoryByID(id);
        Set<Product> products = category.getProducts();
        List result = ProductConverter.convert(products, wizardState.getLocale());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> create(@RequestBody AdminBaseNameObject adminBaseNameObject) {
        categoryService.createCategory(adminBaseNameObject);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("getAllCategoriesAdmin")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List> getAllCategoriesAdmin() {
        List result = new ArrayList();
        Iterable<Category> categories = categoryService.getAllCategories();
        categories.forEach(category -> result.add(new AdminBaseNameObject(category.getId(),
                CommonConverter.getStringFromLocaleNameObjects(category.getCategoryNames(), Locale.Russian),
                CommonConverter.getStringFromLocaleNameObjects(category.getCategoryNames(), Locale.English),
                CommonConverter.getStringFromLocaleNameObjects(category.getCategoryNames(), Locale.Ukraine)
        )));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
