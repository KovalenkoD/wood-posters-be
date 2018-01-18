package com.woodposters.entity.quote;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="contact")
public class Contact {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="contact_id")
    private long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="comment")
    private String comment;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "sales_order_id", nullable = false)
    @JsonIgnore
    private SalesOrder salesOrder;

    public Contact(){}

    public Contact(String firstName, String lastName, String comment, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.comment = comment;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }
}
