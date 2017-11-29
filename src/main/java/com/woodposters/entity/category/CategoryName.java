package com.woodposters.entity.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="category_name")
public class CategoryName implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="locale")
    private String locale;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    @JsonIgnore
    private Category category;

    public CategoryName(){}

    public CategoryName(String name, String locale, Category category){
        this.name=name;
        this.locale=locale;
        this.category = category;
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

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
