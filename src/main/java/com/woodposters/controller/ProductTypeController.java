package com.woodposters.controller;

import com.woodposters.beans.WizardState;
import com.woodposters.converters.ProductConverter;
import com.woodposters.converters.ProductTypeConverter;
import com.woodposters.entity.product.Product;
import com.woodposters.entity.productType.ProductType;
import com.woodposters.repository.ProductTypeRepository;
import com.woodposters.service.productType.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private WizardState wizardState;

    @GetMapping("getAllProductTypes")
    public ResponseEntity<List> getAllProductTypes() {
        List result = new ArrayList();
        Iterable<ProductType> productTypes = productTypeService.getAllProductTypes();
        productTypes.forEach(productType -> result.add(ProductTypeConverter.convert(productType, wizardState.getLocale())));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("getAllVisibleProductTypes")
    public ResponseEntity<List> getAllVisibleProductTypes() {
        List result = new ArrayList();
        List<ProductType> productTypes = productTypeService.getAllVisibleProductTypes();
        productTypes.forEach(productType -> result.add(ProductTypeConverter.convert(productType, wizardState.getLocale())));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("getProductsByProductType/{id}")
    public ResponseEntity<List> getProductsByProductType(@PathVariable("id") Long id) {
        ProductType category = productTypeService.findProductType(id);
        Set<Product> products = category.getProducts();
        List result = ProductConverter.convert(products, wizardState.getLocale());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
