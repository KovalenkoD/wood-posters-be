package com.woodposters.entity.product;

import com.woodposters.entity.category.Category;
import com.woodposters.entity.material.Material;
import com.woodposters.entity.productType.ProductType;
import com.woodposters.entity.technology.Technology;
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

    public BundleProduct(double price, String size, short status, Set<ProductName> productNames, Set<ProductDescription> productDescriptions, String bundleImage, Set<Category> categories, Set<ProductType> productTypes, Set<Technology> technologies, Set<Material> materials) {
        super(price, size, status, productNames, productDescriptions, categories, productTypes, technologies, materials);
        this.bundleImage = bundleImage;
    }
}
