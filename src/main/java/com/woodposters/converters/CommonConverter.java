package com.woodposters.converters;

import com.woodposters.beans.Locale;
import com.woodposters.entity.LocaleDescription;
import com.woodposters.entity.LocaleName;

import java.util.Collection;

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
}
