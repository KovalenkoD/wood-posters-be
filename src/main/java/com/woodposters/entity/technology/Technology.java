package com.woodposters.entity.technology;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woodposters.entity.product.Product;
import com.woodposters.entity.productType.ProductTypeName;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="technology")
public class Technology implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="technology_id")
    private long id;

    @OneToMany(mappedBy="technology")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Set<TechnologyName> technologyNames;


    @ManyToMany(mappedBy = "technologies")
    @JsonIgnore
    private Set<Product> products;

    public Technology(){}

    public Technology(Set<TechnologyName> technologyNames, Set<Product> products) {
        this.technologyNames = technologyNames;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<TechnologyName> getTechnologyNames() {
        return technologyNames;
    }

    public void setTechnologyNames(Set<TechnologyName> technologyNames) {
        this.technologyNames = technologyNames;
    }
}
