package com.woodposters.entity.productType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woodposters.beans.Locale;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="product_type_name")
public class ProductTypeName implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="locale")
    @Enumerated(EnumType.STRING)
    private Locale locale;

    @ManyToOne
    @JoinColumn(name="product_type_id", nullable=false)
    @JsonIgnore
    private ProductType productType;

    public ProductTypeName(){}

    public ProductTypeName(String name, Locale locale, ProductType productType){
        this.name=name;
        this.locale=locale;
        this.productType = productType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
