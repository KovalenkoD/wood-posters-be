package com.woodposters.entity.material;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woodposters.beans.Locale;
import com.woodposters.entity.LocaleName;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="material_name")
public class MaterialName implements LocaleName, Serializable {
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
    @JoinColumn(name="material_id", nullable=false)
    @JsonIgnore
    private Material material;

    public MaterialName(){}

    public MaterialName(String name, Locale locale, Material material){
        this.name=name;
        this.locale=locale;
        this.material = material;
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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
