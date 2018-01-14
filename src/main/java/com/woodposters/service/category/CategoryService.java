package com.woodposters.service.category;

import com.woodposters.entity.adminModel.AdminBaseNameObject;
import com.woodposters.entity.adminModel.AdminProductType;
import com.woodposters.entity.category.Category;
import com.woodposters.entity.productType.ProductType;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface CategoryService {

    Iterable<Category> getAllCategories();

    Category findCategoryByID(long id);

    @Secured({"ROLE_ADMIN"})
    void createCategory(AdminBaseNameObject adminBaseNameObject);

}

