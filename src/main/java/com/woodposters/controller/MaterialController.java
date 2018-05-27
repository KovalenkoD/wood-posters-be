package com.woodposters.controller;

import com.woodposters.beans.Locale;
import com.woodposters.beans.WizardState;
import com.woodposters.converters.CategoryConverter;
import com.woodposters.converters.CommonConverter;
import com.woodposters.converters.MaterialConverter;
import com.woodposters.entity.adminModel.AdminBaseNameObject;
import com.woodposters.entity.adminModel.AdminProductType;
import com.woodposters.entity.category.Category;
import com.woodposters.entity.material.Material;
import com.woodposters.entity.productType.ProductType;
import com.woodposters.repository.MaterialRepository;
import com.woodposters.service.material.MaterialService;
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
@RequestMapping("materials")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private WizardState wizardState;

    @GetMapping("getAllMaterials")
    public ResponseEntity<List> getAllMaterials() {
        List result = new ArrayList();
        Iterable<Material> materials = materialService.getAllMaterials();
        materials.forEach(material -> result.add(MaterialConverter.convert(material, wizardState.getLocale())));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> create(@RequestBody AdminBaseNameObject adminBaseNameObject) {
        materialService.createMaterial(adminBaseNameObject);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> update(@RequestBody AdminBaseNameObject adminBaseNameObject) {
        materialService.updateMaterial(adminBaseNameObject);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> delete(@RequestBody AdminBaseNameObject adminBaseNameObject) {
        materialService.deleteMaterial(adminBaseNameObject);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("getAllMaterialsAdmin")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List> getAllMaterialsAdmin() {
        List result = new ArrayList();
        Iterable<Material> materials = materialService.getAllMaterials();
        materials.forEach(material -> result.add(new AdminBaseNameObject(material.getId(),
                CommonConverter.getStringFromLocaleNameObjects(material.getMaterialNames(), Locale.Russian),
                CommonConverter.getStringFromLocaleNameObjects(material.getMaterialNames(), Locale.English),
                CommonConverter.getStringFromLocaleNameObjects(material.getMaterialNames(), Locale.Ukraine)
        )));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
