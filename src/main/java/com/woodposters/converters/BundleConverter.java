package com.woodposters.converters;

import com.woodposters.beans.Locale;
import com.woodposters.entity.product.BundleChildProduct;
import com.woodposters.entity.product.BundleProduct;
import com.woodposters.entity.product.Product;

import java.util.*;

public class BundleConverter  extends CommonConverter {

    public static List<Map<String, Object>> convertBundles(List<BundleProduct> bundleProducts, Locale locale){
        List<Map<String, Object>> bundleItems = new ArrayList<>();
        bundleProducts.forEach(bundleProduct -> bundleItems.add(convert(bundleProduct, locale)));
        return bundleItems;
    }

    public static Map<String, Object> convert(BundleProduct bundleProduct, Locale locale) {
       Map<String, Object> result = ProductConverter.convert(bundleProduct, locale);
       result.put("bundleImage", bundleProduct.getBundleImage());
       result.put("childItems", convertBundleChildItems(bundleProduct.getBundleChildProducts(), locale));
       return result;
    }

    private static List<Map<String, Object>> convertBundleChildItems(Set<BundleChildProduct> bundleChildProducts, Locale locale){
        List<Map<String, Object>> bundleChildItems = new ArrayList<>();
        bundleChildProducts.forEach(bundleChildProduct -> bundleChildItems.add(convertBundleChildItem(bundleChildProduct, locale)));
        return bundleChildItems;
    }

    private static Map<String, Object> convertBundleChildItem(BundleChildProduct bundleChildProduct, Locale locale){
        Map<String, Object> bundleChildItemMap = new HashMap<>();
        bundleChildItemMap.put("x_coordinate", bundleChildProduct.getX_coordinate());
        bundleChildItemMap.put("y_coordinate", bundleChildProduct.getY_coordinate());
        bundleChildItemMap.put("product", convertBundleChildProductItem(bundleChildProduct.getProduct(), locale));
        return bundleChildItemMap;
    }

    private static Map<String, Object> convertBundleChildProductItem(Product product, Locale locale){
        Map<String, Object> productMap = new HashMap<>();
        productMap.put("id", product.getId());
        productMap.put("name", getStringFromLocaleNameObjects(product.getProductNames(), locale));
        productMap.put("image", product.getImage());
        productMap.put("price", product.getPrice());
        return productMap;
    }
}
