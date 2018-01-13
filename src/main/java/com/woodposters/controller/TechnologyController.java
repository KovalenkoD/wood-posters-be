package com.woodposters.controller;

import com.woodposters.beans.WizardState;
import com.woodposters.converters.CategoryConverter;
import com.woodposters.converters.TechnologyConverter;
import com.woodposters.entity.category.Category;
import com.woodposters.entity.technology.Technology;
import com.woodposters.repository.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("technology")
public class TechnologyController {

    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private WizardState wizardState;

    @GetMapping("getAllTechnologies")
    public ResponseEntity<List> getAllTechnologies() {
        List result = new ArrayList();
        Iterable<Technology> technologies = technologyRepository.findAll();
        technologies.forEach(technology -> result.add(TechnologyConverter.convert(technology, wizardState.getLocale())));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
