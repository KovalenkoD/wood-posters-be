package com.woodposters.controller;

import com.woodposters.beans.WizardState;
import com.woodposters.converters.CategoryConverter;
import com.woodposters.converters.MaterialConverter;
import com.woodposters.entity.category.Category;
import com.woodposters.entity.material.Material;
import com.woodposters.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("materials")
public class MaterialController {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private WizardState wizardState;

    @GetMapping("getAllMaterials")
    public ResponseEntity<List> getAllMaterials() {
        List result = new ArrayList();
        Iterable<Material> materials = materialRepository.findAll();
        materials.forEach(material -> result.add(MaterialConverter.convert(material, wizardState.getLocale())));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
