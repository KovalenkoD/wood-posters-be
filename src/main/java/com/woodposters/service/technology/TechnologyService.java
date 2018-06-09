package com.woodposters.service.technology;

import com.woodposters.entity.adminModel.AdminProduct;
import com.woodposters.entity.adminModel.AdminProductType;
import com.woodposters.entity.product.BundleProduct;
import com.woodposters.entity.product.Product;
import com.woodposters.entity.productType.ProductType;
import com.woodposters.entity.technology.Technology;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface TechnologyService {

    Iterable<Technology> getAllTechnologies();

    Technology findTechnology(long id);

    @Secured({"ROLE_ADMIN"})
    void createTechnology(AdminProductType adminProductType);

    @Secured({"ROLE_ADMIN"})
    void updateTechnology(AdminProductType adminProductType);

    @Secured({"ROLE_ADMIN"})
    void deleteTechnology(AdminProductType adminProductType);


}

