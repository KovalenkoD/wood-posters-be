package com.woodposters.entity.product;

import com.woodposters.entity.category.Category;
import com.woodposters.entity.productType.ProductType;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name="product")
@DiscriminatorValue("BP")
@Indexed
public class BundleProduct extends Product {

    @Column(name="bundle_image")
    private String bundleImage;

    public String getBundleImage() {
        return bundleImage;
    }

    public void setBundleImage(String bundleImage) {
        this.bundleImage = bundleImage;
    }


    public BundleProduct() {
        this.bundleImage = bundleImage;
    }

    public BundleProduct(double price, String type, short status, Set<ProductName> productNames, String bundleImage, Set<Category> categories, Set<ProductType> productTypes) {
        super(price, type, status, productNames, categories, productTypes);
        this.bundleImage = bundleImage;
    }
}
