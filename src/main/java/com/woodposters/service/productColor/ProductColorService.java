package com.woodposters.service.productColor;

import com.woodposters.entity.adminModel.AdminBaseNameObject;
import com.woodposters.entity.productColor.ProductColor;
import org.springframework.security.access.annotation.Secured;

public interface ProductColorService {

    Iterable<ProductColor> getAllProductColors();

    ProductColor findProductColorByID(long id);

    @Secured({"ROLE_ADMIN"})
    void createProductColor(AdminBaseNameObject adminBaseNameObject);

    @Secured({"ROLE_ADMIN"})
    void updateProductColor(AdminBaseNameObject adminBaseNameObject);

    @Secured({"ROLE_ADMIN"})
    void deleteProductColor(AdminBaseNameObject adminBaseNameObject);


}

