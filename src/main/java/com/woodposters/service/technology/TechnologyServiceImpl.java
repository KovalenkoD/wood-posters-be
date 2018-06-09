package com.woodposters.service.technology;

import com.woodposters.beans.Locale;
import com.woodposters.entity.adminModel.AdminProductType;
import com.woodposters.entity.technology.Technology;
import com.woodposters.entity.technology.TechnologyName;
import com.woodposters.repository.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Service
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Iterable<Technology> getAllTechnologies() {
        return technologyRepository.findAll();
    }

    @Override
    public Technology findTechnology(long id) {
        return technologyRepository.findOne(id);
    }

    @Override
    public void createTechnology(AdminProductType adminProductType) {
        createTechnology(adminProductType.getNameRU(), adminProductType.getNameEN(), adminProductType.getNameUA());
    }

    @Override
    public void updateTechnology(AdminProductType adminProductType) {
        Technology technology = technologyRepository.findOne(adminProductType.getId());
        Set<TechnologyName> technologyNames = technology.getTechnologyNames();

        findAndUpdateTechnology(Locale.English, technologyNames, adminProductType.getNameEN(), technology);
        findAndUpdateTechnology(Locale.Russian, technologyNames, adminProductType.getNameRU(), technology);
        findAndUpdateTechnology(Locale.Ukraine, technologyNames, adminProductType.getNameUA(), technology);

        technologyRepository.save(technology);
    }

    @Override
    public void deleteTechnology(AdminProductType adminProductType) {
        technologyRepository.delete(adminProductType.getId());
    }

    private TechnologyName findAndUpdateTechnology(Locale locale, Set<TechnologyName> technologyNames, String name, Technology technology) {
        for (TechnologyName technologyName : technologyNames) {
            if(locale.equals(technologyName.getLocale())){
                technologyName.setName(name);
                return technologyName;
            }
        }
        TechnologyName technologyName = new TechnologyName(name,  locale, technology);
        technologyNames.add(technologyName);
        return technologyName;
    }


    private Technology createTechnology(String russianName, String englishName, String ukrainianName){
        Technology technology = new Technology();
        TechnologyName technologyNameEN = new TechnologyName(englishName,  Locale.English, technology);
        TechnologyName technologyNameRU = new TechnologyName(russianName, Locale.Russian, technology);
        TechnologyName technologyNameUA = new TechnologyName(ukrainianName, Locale.Ukraine, technology);

        technology.setTechnologyNames(new HashSet<>(Arrays.asList(technologyNameEN, technologyNameRU, technologyNameUA)));
        technologyRepository.save(technology);
        return technology;
    }
}
