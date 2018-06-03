package com.woodposters.entity.adminModel;

public class AdminBundleProductItem {
    private Integer id;
    private Integer xCoordinate;
    private Integer yCoordinate;
    private Integer productId;

    public AdminBundleProductItem() {
    }

    public AdminBundleProductItem(Integer id, Integer xCoordinate, Integer yCoordinate, Integer productId) {
        this.id = id;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Integer xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Integer getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Integer yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
