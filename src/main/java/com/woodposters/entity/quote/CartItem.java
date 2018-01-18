package com.woodposters.entity.quote;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woodposters.entity.product.Product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="cart_item")
public class CartItem implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="cart_item_id")
    private long id;

    @Column(name="cart_item_count")
    private int count;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sales_order_id", nullable = false)
    @JsonIgnore
    private SalesOrder salesOrder;

    public CartItem() {

    }

    public CartItem(int count, Product product, SalesOrder salesOrder) {
        this.count = count;
        this.product = product;
        this.salesOrder = salesOrder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getProductId(){
        return this.product.getId();
    }

    public double calculatedPrice(){
        return count * product.getPrice();
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }
}
