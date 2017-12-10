package com.woodposters.entity.product;

import com.woodposters.entity.category.Category;
import com.woodposters.entity.material.Material;
import com.woodposters.entity.productType.ProductType;
import com.woodposters.entity.technology.Technology;
import org.hibernate.annotations.Cascade;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="product")
@DiscriminatorValue("BP")
@Indexed
public class BundleProduct extends Product {

    @Column(name="bundle_image")
    private String bundleImage;

    @OneToMany(mappedBy="bundleProduct")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Set<BundleChildProduct> bundleChildProducts;

    public String getBundleImage() {
        return bundleImage;
    }

    public void setBundleImage(String bundleImage) {
        this.bundleImage = bundleImage;
    }

    public Set<BundleChildProduct> getBundleChildProducts() {
        return bundleChildProducts;
    }

    public void setBundleChildProducts(Set<BundleChildProduct> bundleChildProducts) {
        this.bundleChildProducts = bundleChildProducts;
    }

    public BundleProduct() {
        this.bundleImage = bundleImage;
    }

    public BundleProduct(double price, String size, short status, Set<ProductName> productNames, Set<ProductDescription> productDescriptions, String bundleImage, Set<Category> categories, ProductType productType, Set<Technology> technologies, Set<Material> materials) {
        super(price, size, status, productNames, productDescriptions, categories, productType, technologies, materials);
        this.bundleImage = bundleImage;
    }


}
