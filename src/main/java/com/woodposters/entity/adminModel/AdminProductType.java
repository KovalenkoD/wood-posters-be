package com.woodposters.entity.adminModel;

public class AdminProductType {
    private long id;
    private String nameRU;
    private String nameEN;
    private String nameUA;
    private String imageURL;
    private short visible;

    public AdminProductType() {}

    public AdminProductType(long id, String nameRU, String nameEN, String nameUA, String imageURL, short visible) {
        this.id = id;
        this.nameRU = nameRU;
        this.nameEN = nameEN;
        this.nameUA = nameUA;
        this.imageURL = imageURL;
        this.visible = visible;
    }

    public AdminProductType(long id, String nameRU, String nameEN, String nameUA, String imageURL) {
        this.id = id;
        this.nameRU = nameRU;
        this.nameEN = nameEN;
        this.nameUA = nameUA;
        this.imageURL = imageURL;
    }

    public short getVisible() {
        return visible;
    }

    public void setVisible(short visible) {
        this.visible = visible;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
