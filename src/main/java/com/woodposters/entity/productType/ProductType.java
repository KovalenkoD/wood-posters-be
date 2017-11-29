package com.woodposters.entity.productType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woodposters.entity.category.CategoryName;
import com.woodposters.entity.product.Product;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="product_type")
public class ProductType implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="product_type_id")
    private long id;

    @OneToMany(mappedBy="productType")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Set<ProductTypeName> productTypeNames;


    @ManyToMany(mappedBy = "productTypes")
    @JsonIgnore
    private Set<Product> products;

    public ProductType(){}

    public ProductType(Set<ProductTypeName> productTypeNames, Set<Product> products) {
        this.productTypeNames = productTypeNames;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<ProductTypeName> getProductTypeNames() {
        return productTypeNames;
    }

    public void setProductTypeNames(Set<ProductTypeName> productTypeNames) {
        this.productTypeNames = productTypeNames;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
