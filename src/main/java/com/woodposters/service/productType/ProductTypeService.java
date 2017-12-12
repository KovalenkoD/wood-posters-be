package com.woodposters.service.productType;

import com.woodposters.entity.product.BundleProduct;
import com.woodposters.entity.product.Product;
import com.woodposters.entity.productType.ProductType;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface ProductTypeService {

    Iterable<ProductType> getAllProductTypes();

    List<ProductType> getAllVisibleProductTypes();

    ProductType findProductType(long id);

}

