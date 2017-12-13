package com.woodposters.service.productType;

import com.woodposters.beans.Locale;
import com.woodposters.entity.adminModel.AdminProductType;
import com.woodposters.entity.productType.ProductType;
import com.woodposters.entity.productType.ProductTypeName;
import com.woodposters.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


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

    @Override
    public void createProductType(AdminProductType adminProductType) {
        createProductType(adminProductType.getNameRU(), adminProductType.getNameEN(), adminProductType.getNameUA(), adminProductType.getImageURL(), (short) 1);
    }

    private ProductType createProductType(String russianName, String englishName, String ukrainianName, String image, short visible){
        ProductType productType = new ProductType();
        ProductTypeName productNameEN = new ProductTypeName(englishName,  Locale.English, productType);
        ProductTypeName productNameRU = new ProductTypeName(russianName, Locale.Russian, productType);
        ProductTypeName productNameUA = new ProductTypeName(ukrainianName, Locale.Ukraine, productType);

        productType.setProductTypeNames(new HashSet<>(Arrays.asList(productNameEN, productNameRU, productNameUA)));
        productType.setVisible(visible);
        productType.setImage(image);
        productTypeRepository.save(productType);
        return productType;
    }
}
