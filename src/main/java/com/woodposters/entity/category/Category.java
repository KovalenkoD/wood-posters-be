package com.woodposters.entity.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woodposters.entity.product.Product;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="category_id")
    private long id;

    @OneToMany(mappedBy="category")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Set<CategoryName> categoryNames;


    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private Set<Product> products;

    public Category(){}

    public Category(Set<CategoryName> categoryNames, Set<Product> products) {
        this.categoryNames = categoryNames;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<CategoryName> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(Set<CategoryName> categoryNames) {
        this.categoryNames = categoryNames;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
