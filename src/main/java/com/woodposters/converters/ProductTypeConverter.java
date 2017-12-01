package com.woodposters.converters;

import com.woodposters.beans.Locale;
import com.woodposters.entity.category.Category;
import com.woodposters.entity.productType.ProductType;

import java.util.HashMap;
import java.util.Map;

public class ProductTypeConverter  extends CommonConverter{

    public static Map<String, Object> convert(ProductType productType, Locale locale) {
        Map<String, Object> convertMap = new HashMap<>();
        convertMap.put("id", productType.getId());
        convertMap.put("name", getStringFromLocaleNameObjects(productType.getProductTypeNames(), locale));
        return convertMap;
    }
}
