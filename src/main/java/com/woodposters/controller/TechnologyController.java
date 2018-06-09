package com.woodposters.controller;

import com.woodposters.beans.Locale;
import com.woodposters.beans.WizardState;
import com.woodposters.converters.CategoryConverter;
import com.woodposters.converters.CommonConverter;
import com.woodposters.converters.TechnologyConverter;
import com.woodposters.entity.adminModel.AdminBaseNameObject;
import com.woodposters.entity.adminModel.AdminProductType;
import com.woodposters.entity.category.Category;
import com.woodposters.entity.material.Material;
import com.woodposters.entity.technology.Technology;
import com.woodposters.repository.TechnologyRepository;
import com.woodposters.service.technology.TechnologyService;
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
@RequestMapping("technology")
public class TechnologyController {

    @Autowired
    private TechnologyService technologyService;

    @Autowired
    private WizardState wizardState;

    @GetMapping("getAllTechnologies")
    public ResponseEntity<List> getAllTechnologies() {
        List result = new ArrayList();
        Iterable<Technology> technologies = technologyService.getAllTechnologies();
        technologies.forEach(technology -> result.add(TechnologyConverter.convert(technology, wizardState.getLocale())));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("getAllTechnologiesAdmin")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List> getAllTechnologiesAdmin() {
        List result = new ArrayList();
        Iterable<Technology> materials = technologyService.getAllTechnologies();
        materials.forEach(material -> result.add(new AdminBaseNameObject(material.getId(),
                CommonConverter.getStringFromLocaleNameObjects(material.getTechnologyNames(), Locale.Russian),
                CommonConverter.getStringFromLocaleNameObjects(material.getTechnologyNames(), Locale.English),
                CommonConverter.getStringFromLocaleNameObjects(material.getTechnologyNames(), Locale.Ukraine)
        )));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> create(@RequestBody AdminProductType adminProductType) {
        technologyService.createTechnology(adminProductType);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> update(@RequestBody AdminProductType adminProductType) {
        technologyService.updateTechnology(adminProductType);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> delete(@RequestBody AdminProductType adminProductType) {
        technologyService.deleteTechnology(adminProductType);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
