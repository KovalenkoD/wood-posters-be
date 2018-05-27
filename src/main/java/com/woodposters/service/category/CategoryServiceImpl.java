package com.woodposters.service.category;

import com.woodposters.beans.Locale;
import com.woodposters.entity.adminModel.AdminBaseNameObject;
import com.woodposters.entity.adminModel.AdminProductType;
import com.woodposters.entity.category.Category;
import com.woodposters.entity.category.CategoryName;
import com.woodposters.entity.productType.ProductType;
import com.woodposters.entity.productType.ProductTypeName;
import com.woodposters.repository.CategoryRepository;
import com.woodposters.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryByID(long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public void createCategory(AdminBaseNameObject adminBaseNameObject) {
        Category category = new Category();
        Set<CategoryName> categoryNames = createCategoryNames(adminBaseNameObject, category);
        category.setCategoryNames(categoryNames);
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(AdminBaseNameObject adminBaseNameObject) {
        categoryRepository.delete(adminBaseNameObject.getId());
    }

    @Override
    public void updateCategory(AdminBaseNameObject adminBaseNameObject) {
        Category category = categoryRepository.findOne(adminBaseNameObject.getId());
        Set<CategoryName> categoryNames = category.getCategoryNames();
        findAndUpdateCategoryName(Locale.English, categoryNames, adminBaseNameObject.getNameEN(), category);
        findAndUpdateCategoryName(Locale.Russian, categoryNames, adminBaseNameObject.getNameRU(), category);
        findAndUpdateCategoryName(Locale.Ukraine, categoryNames, adminBaseNameObject.getNameUA(), category);
        category.setCategoryNames(categoryNames);
        categoryRepository.save(category);
    }

    private CategoryName findAndUpdateCategoryName(Locale locale, Set<CategoryName> categoryNames, String name, Category category) {
        for (CategoryName categoryName : categoryNames) {
            if(locale.equals(categoryName.getLocale())){
                categoryName.setName(name);
                return categoryName;
            }
        }
        CategoryName categoryName = new CategoryName(name,  locale, category);
        categoryNames.add(categoryName);
        return categoryName;
    }

    private Set<CategoryName> createCategoryNames(AdminBaseNameObject adminBaseNameObject, Category category){
        CategoryName categoryNameEN = new CategoryName(adminBaseNameObject.getNameEN(),  Locale.English, category);
        CategoryName categoryNameRU = new CategoryName(adminBaseNameObject.getNameRU(), Locale.Russian, category);
        CategoryName categoryNameUA = new CategoryName(adminBaseNameObject.getNameUA(), Locale.Ukraine, category);
        return new HashSet<>(Arrays.asList(categoryNameEN, categoryNameRU, categoryNameUA));
    }

}
