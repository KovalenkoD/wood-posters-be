package com.woodposters.converters;

import com.woodposters.beans.Locale;
import com.woodposters.entity.LocaleDescription;
import com.woodposters.entity.LocaleName;
import com.woodposters.entity.category.Category;
import com.woodposters.entity.material.Material;
import com.woodposters.entity.product.ProductImage;
import com.woodposters.entity.technology.Technology;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class CommonConverter {
    public static String getStringFromLocaleNameObjects(Collection<? extends LocaleName> localeNames, Locale locale){
        for(LocaleName localeName:localeNames){
            if(locale.equals(localeName.getLocale())){
                return localeName.getName();
            }
        }
        return "";
    }

    public static String getStringFromLocaleDescriptionObjects(Collection<? extends LocaleDescription> localeDescriptions, Locale locale){
        for(LocaleDescription localeDescription:localeDescriptions){
            if(locale.equals(localeDescription.getLocale())){
                return localeDescription.getDescription();
            }
        }
        return "";
    }

    public static Set<Long> convertTechnologySetToIntIDs(Set<Technology> technologies){
        Set<Long> technologyIDs = new HashSet<>();
        technologies.forEach(technology -> technologyIDs.add(technology.getId()));
        return technologyIDs;
    }

    public static Set<Long> convertCategoriesSetToIntIDs(Set<Category> categories){
        Set<Long> categoriesIDs = new HashSet<>();
        categories.forEach(category -> categoriesIDs.add(category.getId()));
        return categoriesIDs;
    }

    public static Set<Long> convertMaterialsSetToIntIDs(Set<Material> materials){
        Set<Long> materialsIDs = new HashSet<>();
        materials.forEach(material -> materialsIDs.add(material.getId()));
        return materialsIDs;
    }

    public static Set<String> convertProductImagesToImages(Set<ProductImage> productImages){
        Set<String> productImagesURLs = new HashSet<>();
        productImages.forEach(productImage -> productImagesURLs.add(productImage.getImage()));
        return productImagesURLs;
    }
}
