package com.woodposters.entity.adminModel;

import java.util.Set;

public class AdminProduct {
    private long id;
    private String russianName;
    private String englishName;
    private String ukrainianName;
    private double price;
    private boolean isBundle;
    private String size;
    private Set<Long> technologyIDs;
    private String russianDescription;
    private String englishDescription;
    private String ukrainianDescription;
    private Set<Long> categoryIDs;
    private Long productTypeID;
    private Set<Long> materialIDs;
    private Set<Long> productColorIDs;
    private short popular;
    private short imagePresentation;
    private Set<String> images;
    private String image;
    private String background;
    private String articul;
    private String bundleImage;
    private Set<AdminBundleProductItem> bundleProductItems;
    private short visible;


    public AdminProduct(){}

    public AdminProduct(long id, String russianName, String englishName, String ukrainianName, double price, boolean isBundle,
                        String size, Set<Long> technologyIDs, String russianDescription, String englishDescription, String ukrainianDescription,
                        Set<Long> categoryIDs, Long productTypeID, Set<Long> materialIDs, Set<Long> productColorIDs, short popular, short imagePresentation, Set<String> images,
                        String image, String background, String articul, short visible
    ) {
        this.id = id;
        this.russianName = russianName;
        this.englishName = englishName;
        this.ukrainianName = ukrainianName;
        this.price = price;
        this.isBundle = isBundle;
        this.size = size;
        this.technologyIDs = technologyIDs;
        this.russianDescription = russianDescription;
        this.englishDescription = englishDescription;
        this.ukrainianDescription = ukrainianDescription;
        this.categoryIDs = categoryIDs;
        this.productTypeID = productTypeID;
        this.materialIDs = materialIDs;
        this.productColorIDs = productColorIDs;
        this.popular = popular;
        this.imagePresentation = imagePresentation;
        this.images = images;
        this.image = image;
        this.background = background;
        this.articul = articul;
        this.visible = visible;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRussianName() {
        return russianName;
    }

    public void setRussianName(String russianName) {
        this.russianName = russianName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getUkrainianName() {
        return ukrainianName;
    }

    public void setUkrainianName(String ukrainianName) {
        this.ukrainianName = ukrainianName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBundle() {
        return isBundle;
    }

    public void setBundle(boolean bundle) {
        isBundle = bundle;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Set<Long> getTechnologyIDs() {
        return technologyIDs;
    }

    public void setTechnologyIDs(Set<Long> technologyIDs) {
        this.technologyIDs = technologyIDs;
    }

    public String getRussianDescription() {
        return russianDescription;
    }

    public void setRussianDescription(String russianDescription) {
        this.russianDescription = russianDescription;
    }

    public String getEnglishDescription() {
        return englishDescription;
    }

    public void setEnglishDescription(String englishDescription) {
        this.englishDescription = englishDescription;
    }

    public String getUkrainianDescription() {
        return ukrainianDescription;
    }

    public void setUkrainianDescription(String ukrainianDescription) {
        this.ukrainianDescription = ukrainianDescription;
    }

    public Set<Long> getCategoryIDs() {
        return categoryIDs;
    }

    public void setCategoryIDs(Set<Long> categoryIDs) {
        this.categoryIDs = categoryIDs;
    }

    public Long getProductTypeID() {
        return productTypeID;
    }

    public void setProductTypeID(Long productTypeID) {
        this.productTypeID = productTypeID;
    }

    public Set<Long> getMaterialIDs() {
        return materialIDs;
    }

    public void setMaterialIDs(Set<Long> materialIDs) {
        this.materialIDs = materialIDs;
    }

    public short getPopular() {
        return popular;
    }

    public void setPopular(short popular) {
        this.popular = popular;
    }

    public short getImagePresentation() {
        return imagePresentation;
    }

    public void setImagePresentation(short imagePresentation) {
        this.imagePresentation = imagePresentation;
    }

    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBundleImage() {
        return bundleImage;
    }

    public void setBundleImage(String bundleImage) {
        this.bundleImage = bundleImage;
    }

    public Set<AdminBundleProductItem> getBundleProductItems() {
        return bundleProductItems;
    }

    public void setBundleProductItems(Set<AdminBundleProductItem> bundleProductItems) {
        this.bundleProductItems = bundleProductItems;
    }

    public String getArticul() {
        return articul;
    }

    public void setArticul(String articul) {
        this.articul = articul;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public Set<Long> getProductColorIDs() {
        return productColorIDs;
    }

    public void setProductColorIDs(Set<Long> productColorIDs) {
        this.productColorIDs = productColorIDs;
    }

    public short getVisible() {
        return visible;
    }

    public void setVisible(short visible) {
        this.visible = visible;
    }
}
