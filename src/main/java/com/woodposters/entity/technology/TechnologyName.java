package com.woodposters.entity.technology;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woodposters.beans.Locale;
import com.woodposters.entity.LocaleName;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="technology_name")
public class TechnologyName implements LocaleName, Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="locale")
    @Enumerated(EnumType.STRING)
    private Locale locale;

    @ManyToOne
    @JoinColumn(name="technology_id", nullable=false)
    @JsonIgnore
    private Technology technology;

    public TechnologyName(){}

    public TechnologyName(String name, Locale locale, Technology technology){
        this.name=name;
        this.locale=locale;
        this.technology = technology;
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

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }
}

