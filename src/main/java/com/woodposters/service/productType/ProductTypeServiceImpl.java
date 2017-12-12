package com.woodposters.service.productType;

import com.woodposters.beans.Locale;
import com.woodposters.entity.category.Category;
import com.woodposters.entity.category.CategoryName;
import com.woodposters.entity.material.Material;
import com.woodposters.entity.material.MaterialName;
import com.woodposters.entity.product.*;
import com.woodposters.entity.productType.ProductType;
import com.woodposters.entity.productType.ProductTypeName;
import com.woodposters.entity.technology.Technology;
import com.woodposters.entity.technology.TechnologyName;
import com.woodposters.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.util.*;


@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Iterable<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    @Override
    public List<ProductType> getAllVisibleProductTypes() {
        List<ProductType> list = entityManager.createQuery("SELECT productType FROM ProductType productType WHERE visible=?")
                .setParameter(1, (short)1).getResultList();
        return list;
    }

    @Override
    public ProductType findProductType(long id) {
        return productTypeRepository.findOne(id);
    }
}
