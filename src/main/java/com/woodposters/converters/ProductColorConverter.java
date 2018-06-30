package com.woodposters.converters;

import com.woodposters.beans.Locale;
import com.woodposters.entity.productColor.ProductColor;

import java.util.HashMap;
import java.util.Map;

/**
 *@author Dmitry Kovalenko
 */
public class ProductColorConverter extends CommonConverter{

    public static Map<String, Object> convert(ProductColor productColor, Locale locale) {
        Map<String, Object> convertMap = new HashMap<>();
        convertMap.put("id", productColor.getId());
        convertMap.put("name", getStringFromLocaleNameObjects(productColor.getProductColorNames(), locale));
        return convertMap;
    }
}

