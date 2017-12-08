package com.woodposters.converters;

import com.woodposters.beans.Locale;
import com.woodposters.entity.material.Material;

import java.util.HashMap;
import java.util.Map;

public class MaterialConverter extends CommonConverter{

    public static Map<String, Object> convert(Material material, Locale locale) {
        Map<String, Object> convertMap = new HashMap<>();
        convertMap.put("id", material.getId());
        convertMap.put("name", getStringFromLocaleNameObjects(material.getMaterialNames(), locale));
        return convertMap;
    }
}
