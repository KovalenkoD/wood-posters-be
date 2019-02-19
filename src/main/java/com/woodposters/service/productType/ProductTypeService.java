package com.woodposters.service.productType;

import com.woodposters.beans.Locale;
import com.woodposters.entity.adminModel.AdminProductType;
import com.woodposters.entity.product.BundleProduct;
import com.woodposters.entity.product.Product;
import com.woodposters.entity.productType.ProductType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductTypeService {

    Iterable<ProductType> getAllProductTypes();

    List<ProductType> getAllVisibleProductTypes();

    ProductType findProductType(long id);

    List getProductsByProductType(Long id, Locale locale);

    @Secured({"ROLE_ADMIN"})
    void createProductType(AdminProductType adminProductType);

    @Secured({"ROLE_ADMIN"})
    void updateProductType(AdminProductType adminProductType);

    @Secured({"ROLE_ADMIN"})
    void deleteProductType(AdminProductType adminProductType);
}

