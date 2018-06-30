package com.woodposters.entity.productColor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woodposters.entity.product.Product;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="product_color")
public class ProductColor implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="product_color_id")
    private long id;

    @OneToMany(mappedBy="productColor")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Set<ProductColorName> productColorNames;


    @ManyToMany(mappedBy = "productColors")
    @JsonIgnore
    private Set<Product> products;

    public ProductColor(){}

    public ProductColor(Set<ProductColorName> productColorNames, Set<Product> products) {
        this.productColorNames = productColorNames;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<ProductColorName> getProductColorNames() {
        return productColorNames;
    }

    public void setProductColorNames(Set<ProductColorName> productColorNames) {
        this.productColorNames = productColorNames;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
