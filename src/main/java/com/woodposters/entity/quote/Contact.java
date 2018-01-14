package com.woodposters.entity.quote;

public class Contact {
    private String firstName;
    private String lastName;
    private String comment;
    private String phone;
    private String email;

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
}
