package com.woodposters.converters;

import com.woodposters.beans.Locale;
import com.woodposters.entity.category.Category;
import com.woodposters.entity.material.Material;
import com.woodposters.entity.product.Product;
import com.woodposters.entity.productType.ProductType;
import com.woodposters.entity.technology.Technology;

import java.util.*;

public class ProductConverter extends CommonConverter {

    public static List<Map<String, Object>> convert(Collection<? extends Product> products, Locale locale) {
        List<Map<String, Object>> productsResult = new ArrayList<>();
        products.forEach(product -> productsResult.add(convert(product, locale)));
        return productsResult;
    }


    public static Map<String, Object> convert(Product product, Locale locale) {
        Map<String, Object> convertMap = new HashMap<>();
        convertMap.put("id", product.getId());
        convertMap.put("price", product.getPrice());
        convertMap.put("size", product.getSize());
        convertMap.put("name", getStringFromLocaleNameObjects(product.getProductNames(), locale));
        convertMap.put("description", getStringFromLocaleDescriptionObjects(product.getProductDescriptions(), locale));
        convertMap.put("categories", convertCategories(product.getCategories(), locale));
        convertMap.put("productTypes", convertProductTypes(product.getProductTypes(), locale));
        convertMap.put("technologies", convertTechnologies(product.getTechnologies(), locale));
        convertMap.put("materials", convertMaterials(product.getMaterials(), locale));
        return convertMap;
    }

    public static List<Map<String, Object>> convertCategories(Set<Category> categories, Locale locale){
        List<Map<String, Object>> categoryConvertResult = new ArrayList<>();
        categories.forEach(category -> categoryConvertResult.add(CategoryConverter.convert(category, locale)));
        return categoryConvertResult;
    }

    public static List<Map<String, Object>> convertProductTypes(Set<ProductType> productTypes, Locale locale){
        List<Map<String, Object>> productTypeConvertResult = new ArrayList<>();
        productTypes.forEach(productType -> productTypeConvertResult.add(ProductTypeConverter.convert(productType, locale)));
        return productTypeConvertResult;
    }

    public static List<Map<String, Object>> convertTechnologies(Set<Technology> technologies, Locale locale){
        List<Map<String, Object>> technologyConvertResult = new ArrayList<>();
        technologies.forEach(technology -> technologyConvertResult.add(TechnologyConverter.convert(technology, locale)));
        return technologyConvertResult;
    }

    public static List<Map<String, Object>> convertMaterials(Set<Material> materials, Locale locale){
        List<Map<String, Object>> materialConvertResult = new ArrayList<>();
        materials.forEach(material -> materialConvertResult.add(MaterialConverter.convert(material, locale)));
        return materialConvertResult;
    }




}
