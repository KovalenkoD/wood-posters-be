package com.woodposters.service.material;

import com.woodposters.entity.adminModel.AdminBaseNameObject;
import com.woodposters.entity.adminModel.AdminProductType;
import com.woodposters.entity.category.Category;
import com.woodposters.entity.material.Material;
import com.woodposters.entity.productType.ProductType;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface MaterialService {

    Iterable<Material> getAllMaterials();

    Material findMaterialByID(long id);

    @Secured({"ROLE_ADMIN"})
    void createMaterial(AdminBaseNameObject adminBaseNameObject);
}

