package com.woodposters.controller;

import com.woodposters.beans.Locale;
import com.woodposters.beans.WizardState;
import com.woodposters.converters.CategoryConverter;
import com.woodposters.converters.CommonConverter;
import com.woodposters.converters.ProductColorConverter;
import com.woodposters.entity.adminModel.AdminBaseNameObject;
import com.woodposters.entity.adminModel.AdminProductType;
import com.woodposters.entity.category.Category;
import com.woodposters.entity.productColor.ProductColor;
import com.woodposters.entity.productType.ProductType;
import com.woodposters.repository.ProductColorRepository;
import com.woodposters.service.productColor.ProductColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("productColors")
public class ProductColorController {

    @Autowired
    private ProductColorService productColorService;

    @Autowired
    private WizardState wizardState;

    @GetMapping("getAllProductColors")
    public ResponseEntity<List> getAllProductColors() {
        List result = new ArrayList();
        Iterable<ProductColor> productColors = productColorService.getAllProductColors();
        productColors.forEach(productColor -> result.add(ProductColorConverter.convert(productColor, wizardState.getLocale())));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> create(@RequestBody AdminBaseNameObject adminBaseNameObject) {
        productColorService.createProductColor(adminBaseNameObject);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> update(@RequestBody AdminBaseNameObject adminBaseNameObject) {
        productColorService.updateProductColor(adminBaseNameObject);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> delete(@RequestBody AdminBaseNameObject adminBaseNameObject) {
        productColorService.deleteProductColor(adminBaseNameObject);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("getAllProductColorsAdmin")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List> getAllProductColorsAdmin() {
        List result = new ArrayList();
        Iterable<ProductColor> productColors = productColorService.getAllProductColors();
        productColors.forEach(productColor -> result.add(new AdminBaseNameObject(productColor.getId(),
                CommonConverter.getStringFromLocaleNameObjects(productColor.getProductColorNames(), Locale.Russian),
                CommonConverter.getStringFromLocaleNameObjects(productColor.getProductColorNames(), Locale.English),
                CommonConverter.getStringFromLocaleNameObjects(productColor.getProductColorNames(), Locale.Ukraine)
        )));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
