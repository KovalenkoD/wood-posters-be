package com.woodposters.entity.adminModel;

public class AdminBaseNameObject {
    private long id;
    private String nameRU;
    private String nameEN;
    private String nameUA;

    public AdminBaseNameObject(){}

    public AdminBaseNameObject(long id, String nameRU, String nameEN, String nameUA) {
        this.id = id;
        this.nameRU = nameRU;
        this.nameEN = nameEN;
        this.nameUA = nameUA;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameRU() {
        return nameRU;
    }

    public void setNameRU(String nameRU) {
        this.nameRU = nameRU;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getNameUA() {
        return nameUA;
    }

    public void setNameUA(String nameUA) {
        this.nameUA = nameUA;
    }
}
