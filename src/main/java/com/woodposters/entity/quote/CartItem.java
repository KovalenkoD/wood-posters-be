package com.woodposters.entity.quote;

import com.woodposters.entity.product.Product;

import java.io.Serializable;

public class CartItem implements Serializable {

    private long id;

    private int count;

    private Product product;

    public CartItem() {

    }

    public CartItem(int count, Product product) {
        this.count = count;
        this.product = product;
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
}
