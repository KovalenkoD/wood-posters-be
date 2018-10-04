package com.woodposters.converters;

import com.woodposters.beans.Locale;
import com.woodposters.entity.category.Category;
import com.woodposters.entity.product.Product;

import java.util.HashMap;
import java.util.Map;

public class CategoryConverter extends CommonConverter{

    public static Map<String, Object> convert(Category category, Locale locale) {
        Map<String, Object> convertMap = new HashMap<>();
        convertMap.put("id", category.getId());
        convertMap.put("name", getStringFromLocaleNameObjects(category.getCategoryNames(), locale));
        return convertMap;
    }

    public static Map<String, Object> convertLight(Category category) {
        Map<String, Object> convertMap = new HashMap<>();
        convertMap.put("id", category.getId());
        return convertMap;
    }
}
