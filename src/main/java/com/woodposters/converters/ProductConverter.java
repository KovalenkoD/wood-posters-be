package com.woodposters.converters;

import com.woodposters.beans.Locale;
import com.woodposters.entity.category.Category;
import com.woodposters.entity.material.Material;
import com.woodposters.entity.product.Product;
import com.woodposters.entity.product.ProductImage;
import com.woodposters.entity.productColor.ProductColor;
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
        convertMap.put("image", product.getImage());
        convertMap.put("name", getStringFromLocaleNameObjects(product.getProductNames(), locale));
        convertMap.put("description", getStringFromLocaleDescriptionObjects(product.getProductDescriptions(), locale));
        convertMap.put("categories", convertCategories(product.getCategories(), locale));
        convertMap.put("productType", ProductTypeConverter.convert(product.getProductType(), locale));
        convertMap.put("technologies", convertTechnologies(product.getTechnologies(), locale));
        convertMap.put("materials", convertMaterials(product.getMaterials(), locale));
        convertMap.put("productColors", convertProductColor(product.getProductColors(), locale));
        convertMap.put("images", convertImages(product.getImages()));
        convertMap.put("imagePresentation", product.getImagePresentation());
        convertMap.put("background", product.getBackground());
        convertMap.put("articul", product.getArticul());

        return convertMap;
    }

    public static List<Map<String, Object>> convertCategories(Set<Category> categories, Locale locale){
        List<Map<String, Object>> categoryConvertResult = new ArrayList<>();
        categories.forEach(category -> categoryConvertResult.add(CategoryConverter.convert(category, locale)));
        return categoryConvertResult;
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

    public static List<Map<String, Object>> convertProductColor(Set<ProductColor> productColors, Locale locale){
        List<Map<String, Object>> productColorConvertResult = new ArrayList<>();
        productColors.forEach(productColor -> productColorConvertResult.add(ProductColorConverter.convert(productColor, locale)));
        return productColorConvertResult;
    }

    public static List<String> convertImages(Set<ProductImage> images){
        if(images == null || images.isEmpty()) return Collections.EMPTY_LIST;
        List<String> imagesResult = new ArrayList<>();
        images.forEach(image -> imagesResult.add(image.getImage()));
        return imagesResult;
    }



}
