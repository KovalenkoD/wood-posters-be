package com.woodposters.controller;

import com.woodposters.beans.Locale;
import com.woodposters.beans.WizardState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("wizard")
public class WizardController {

    @Autowired
    private WizardState wizardState;

    @GetMapping("getLocales")
    public ResponseEntity<List<Map<String, Object>>> getAllProducts() {
        Locale currentLocale = wizardState.getLocale();
        List<Map<String, Object>> locales = new ArrayList<>();
        for (Locale locale : Locale.values()) {
            Map<String, Object> localeMap = new HashMap<>();
            localeMap.put("name", locale.name());
            localeMap.put("isSelected", currentLocale.equals(locale));
            locales.add(localeMap);
        }
        return new ResponseEntity<>(locales, HttpStatus.OK);
    }

    @GetMapping("changeLocale/{locale}")
    public ResponseEntity<Void> addProducts(@PathVariable("locale") String locale) {
        wizardState.setLocale(Locale.valueOf(locale));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
