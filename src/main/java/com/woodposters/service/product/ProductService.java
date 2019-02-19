package com.woodposters.service.product;

import com.woodposters.beans.Locale;
import com.woodposters.entity.adminModel.AdminProduct;
import com.woodposters.entity.product.BundleProduct;
import com.woodposters.entity.product.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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

    Map getProductsByCategory(Long id, Locale locale);

    Stream<Product> findRelatedProducts(long id);

    @Secured({"ROLE_ADMIN"})
    void createProduct(AdminProduct adminProduct);

    @Secured({"ROLE_ADMIN"})
    void updateProduct(AdminProduct adminProduct);

    @Secured({"ROLE_ADMIN"})
    void createBundle(AdminProduct adminProduct);

}

