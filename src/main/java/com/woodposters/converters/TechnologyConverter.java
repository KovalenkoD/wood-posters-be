package com.woodposters.converters;

import com.woodposters.beans.Locale;
import com.woodposters.entity.category.Category;
import com.woodposters.entity.technology.Technology;

import java.util.HashMap;
import java.util.Map;

public class TechnologyConverter extends CommonConverter{

    public static Map<String, Object> convert(Technology technology, Locale locale) {
        Map<String, Object> convertMap = new HashMap<>();
        convertMap.put("id", technology.getId());
        convertMap.put("name", getStringFromLocaleNameObjects(technology.getTechnologyNames(), locale));
        return convertMap;
    }
}
