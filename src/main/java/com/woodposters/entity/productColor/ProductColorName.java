package com.woodposters.entity.productColor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woodposters.beans.Locale;
import com.woodposters.entity.LocaleName;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="product_color_name")
public class ProductColorName implements LocaleName, Serializable {
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
    @JoinColumn(name="product_color_id", nullable=false)
    @JsonIgnore
    private ProductColor productColor;

    public ProductColorName(){}

    public ProductColorName(String name, Locale locale, ProductColor productColor){
        this.name=name;
        this.locale=locale;
        this.productColor = productColor;
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

    public ProductColor getProductColor() {
        return productColor;
    }

    public void setProductColor(ProductColor productColor) {
        this.productColor = productColor;
    }
}
