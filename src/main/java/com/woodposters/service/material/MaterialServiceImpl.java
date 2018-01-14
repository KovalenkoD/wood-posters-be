package com.woodposters.service.material;

import com.woodposters.beans.Locale;
import com.woodposters.entity.adminModel.AdminBaseNameObject;
import com.woodposters.entity.adminModel.AdminProductType;
import com.woodposters.entity.category.Category;
import com.woodposters.entity.category.CategoryName;
import com.woodposters.entity.material.Material;
import com.woodposters.entity.material.MaterialName;
import com.woodposters.entity.productType.ProductType;
import com.woodposters.entity.productType.ProductTypeName;
import com.woodposters.repository.CategoryRepository;
import com.woodposters.repository.MaterialRepository;
import com.woodposters.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Iterable<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    @Override
    public Material findMaterialByID(long id) {
        return materialRepository.findOne(id);
    }

    @Override
    public void createMaterial(AdminBaseNameObject adminBaseNameObject) {
        Material material = new Material();
        Set<MaterialName> materialNames = createMaterialNames(adminBaseNameObject, material);
        material.setMaterialNames(materialNames);
        materialRepository.save(material);
    }

    private Set<MaterialName> createMaterialNames(AdminBaseNameObject adminBaseNameObject, Material material){
        MaterialName materialNameEN = new MaterialName(adminBaseNameObject.getNameEN(),  Locale.English, material);
        MaterialName materialNameRU = new MaterialName(adminBaseNameObject.getNameRU(), Locale.Russian, material);
        MaterialName materialNameUA = new MaterialName(adminBaseNameObject.getNameUA(), Locale.Ukraine, material);
        return new HashSet<>(Arrays.asList(materialNameEN, materialNameRU, materialNameUA));
    }

}
