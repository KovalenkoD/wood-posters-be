package com.woodposters.service.product;

import com.woodposters.entity.adminModel.AdminProduct;
import com.woodposters.entity.product.BundleProduct;
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

    List<Product> getMostPopularProducts(final String discriminator, short popular);

    List<BundleProduct> getMostPopularBundleProducts(final String discriminator, short popular);

    List<BundleProduct> getAllBundles();

    List<Product> findRelatedProducts(long id);

    @Secured({"ROLE_ADMIN"})
    void createProduct(AdminProduct adminProduct);


    @Secured({"ROLE_ADMIN"})
    void createBundle(AdminProduct adminProduct);

}

