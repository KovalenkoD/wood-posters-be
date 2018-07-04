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

    @OneToOne(mappedBy = "salesOrder")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private DeliveryAddress deliveryAddress;

    @OneToMany(mappedBy="salesOrder")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Set<CartItem> cartItems;

    @Column(name="payment_type")
    private short paymentType;

    @Column(name="payment_status")
    private short paymentStatus;

    @Column(name="payment_transaction")
    private String paymentTransaction;

    @Column(name="payment_id")
    private String paymentId;



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

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public short getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(short paymentType) {
        this.paymentType = paymentType;
    }

    public short getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(short paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentTransaction() {
        return paymentTransaction;
    }

    public void setPaymentTransaction(String paymentTransaction) {
        this.paymentTransaction = paymentTransaction;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
