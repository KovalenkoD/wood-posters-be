package com.woodposters.service.product;

import com.woodposters.entity.product.Product;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface ProductService {

    List<Product> getProductsByType(String type);

    @Secured({"ROLE_ADMIN"})
    void addProducts();

    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    void deleteProduct(Long id);

    List<Product> getAllProducts();
}

