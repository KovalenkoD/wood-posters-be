package com.woodposters.entity.quote;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 *@author Dmitry Kovalenko
 */

@Entity
@Table(name="delivery_address")
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="delivery_address_id")
    private long id;

    @Column(name="city")
    private String city;

    @Column(name="full_address")
    private String fullAddress;

    @Column(name="postal_department")
    private String postalDepartment;

    @Column(name="delivery_to_shop")
    private short deliveryToShop;

    @ManyToOne
    @JoinColumn(name = "sales_order_id", nullable = false)
    @JsonIgnore
    private SalesOrder salesOrder;

    public DeliveryAddress() {
    }

    public DeliveryAddress(String city, String fullAddress, String postalDepartment, short deliveryToShop, SalesOrder salesOrder) {
        this.city = city;
        this.fullAddress = fullAddress;
        this.postalDepartment = postalDepartment;
        this.deliveryToShop = deliveryToShop;
        this.salesOrder = salesOrder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getPostalDepartment() {
        return postalDepartment;
    }

    public void setPostalDepartment(String postalDepartment) {
        this.postalDepartment = postalDepartment;
    }

    public short getDeliveryToShop() {
        return deliveryToShop;
    }

    public void setDeliveryToShop(short deliveryToShop) {
        this.deliveryToShop = deliveryToShop;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }
}
