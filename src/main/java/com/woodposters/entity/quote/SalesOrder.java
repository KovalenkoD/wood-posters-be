package com.woodposters.entity.quote;

import org.hibernate.annotations.Cascade;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="sales_order")
public class SalesOrder implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="sales_order_id")
    private long id;

    @Transient
    private int count;

    @Transient
    private double fullPrice;

    @Column(name="status")
    private short status;

    @OneToOne(mappedBy = "salesOrder")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Contact contact;

    @OneToMany(mappedBy="salesOrder")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Set<CartItem> cartItems;

    public SalesOrder() {
        this.cartItems = new HashSet<>();
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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }
}
