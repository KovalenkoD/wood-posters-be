package com.woodposters.controller;

import com.woodposters.beans.Locale;
import com.woodposters.beans.WizardState;
import com.woodposters.converters.CommonConverter;
import com.woodposters.converters.ProductConverter;
import com.woodposters.converters.ProductTypeConverter;
import com.woodposters.entity.adminModel.AdminProductType;
import com.woodposters.entity.product.Product;
import com.woodposters.entity.productType.ProductType;
import com.woodposters.entity.productType.ProductTypeName;
import com.woodposters.repository.ProductTypeRepository;
import com.woodposters.service.productType.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private WizardState wizardState;



    @GetMapping("getAllVisibleProductTypes")
    public ResponseEntity<List> getAllVisibleProductTypes() {
        List result = new ArrayList();
        List<ProductType> productTypes = productTypeService.getAllVisibleProductTypes();
        productTypes.forEach(productType -> result.add(ProductTypeConverter.convert(productType, wizardState.getLocale())));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("getProductsByProductType/{id}")
    public ResponseEntity<List> getProductsByProductType(@PathVariable("id") Long id) {
        List products = productTypeService.getProductsByProductType(id, wizardState.getLocale());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @RequestMapping(value = "create", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> create(@RequestBody AdminProductType adminProductType) {
        productTypeService.createProductType(adminProductType);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> update(@RequestBody AdminProductType adminProductType) {
        productTypeService.updateProductType(adminProductType);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> delete(@RequestBody AdminProductType adminProductType) {
        productTypeService.deleteProductType(adminProductType);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("getAllProductTypes")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List> getAllProductTypes() {
        List result = new ArrayList();
        Iterable<ProductType> productTypes = productTypeService.getAllProductTypes();
        productTypes.forEach(productType -> result.add(new AdminProductType(productType.getId(),
                CommonConverter.getStringFromLocaleNameObjects(productType.getProductTypeNames(), Locale.Russian),
                CommonConverter.getStringFromLocaleNameObjects(productType.getProductTypeNames(), Locale.English),
                CommonConverter.getStringFromLocaleNameObjects(productType.getProductTypeNames(), Locale.Ukraine),
                productType.getImage(),
                productType.getBackground(),
                productType.getVisible())));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("getProductTypeById/{id}")
    public ResponseEntity<Map> getProductTypeById(@PathVariable("id") Long id) {
        ProductType productType = productTypeService.findProductType(id);
        return new ResponseEntity<>(ProductTypeConverter.convert(productType, wizardState.getLocale()), HttpStatus.OK);
    }

}
