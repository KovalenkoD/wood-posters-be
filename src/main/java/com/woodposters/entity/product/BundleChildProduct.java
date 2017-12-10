package com.woodposters.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="bundle_child")
public class BundleChildProduct implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="x_coordinate")
    private int x_coordinate;

    @Column(name="y_coordinate")
    private int y_coordinate;

    @ManyToOne
    @JoinColumn(name="bundle_product_id", nullable=false, referencedColumnName = "product_id")
    @JsonIgnore
    private BundleProduct bundleProduct;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;

    public BundleChildProduct(){}

    public BundleChildProduct(int x_coordinate, int y_coordinate, BundleProduct bundleProduct, Product product) {
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
        this.bundleProduct = bundleProduct;
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getX_coordinate() {
        return x_coordinate;
    }

    public void setX_coordinate(int x_coordinate) {
        this.x_coordinate = x_coordinate;
    }

    public int getY_coordinate() {
        return y_coordinate;
    }

    public void setY_coordinate(int y_coordinate) {
        this.y_coordinate = y_coordinate;
    }

    public BundleProduct getBundleProduct() {
        return bundleProduct;
    }

    public void setBundleProduct(BundleProduct bundleProduct) {
        this.bundleProduct = bundleProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
