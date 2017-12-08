package com.woodposters.entity.material;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woodposters.entity.product.Product;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="material")
public class Material implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="material_id")
    private long id;

    @OneToMany(mappedBy="material")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Set<MaterialName> materialNames;


    @ManyToMany(mappedBy = "materials")
    @JsonIgnore
    private Set<Product> products;

    public Material(){}

    public Material(Set<MaterialName> categoryNames, Set<Product> products) {
        this.materialNames = categoryNames;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<MaterialName> getMaterialNames() {
        return materialNames;
    }

    public void setMaterialNames(Set<MaterialName> materialNames) {
        this.materialNames = materialNames;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
