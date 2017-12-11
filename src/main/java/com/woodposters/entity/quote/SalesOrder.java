package com.woodposters.entity.quote;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SalesOrder implements Serializable {

    private long id;

    private int count;

    private double fullPrice;

    public SalesOrder() {
        this.cartItems = new HashSet<>();
    }

    private Set<CartItem> cartItems;

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

    public double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
