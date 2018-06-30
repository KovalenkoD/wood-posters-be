package com.woodposters.service.productColor;

import com.woodposters.beans.Locale;
import com.woodposters.entity.adminModel.AdminBaseNameObject;
import com.woodposters.entity.productColor.ProductColor;
import com.woodposters.entity.productColor.ProductColorName;
import com.woodposters.repository.ProductColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Service
public class ProductColorServiceImpl implements ProductColorService {

    @Autowired
    private ProductColorRepository productColorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Iterable<ProductColor> getAllProductColors() {
        return productColorRepository.findAll();
    }

    @Override
    public ProductColor findProductColorByID(long id) {
        return productColorRepository.findOne(id);
    }

    @Override
    public void createProductColor(AdminBaseNameObject adminBaseNameObject) {
        ProductColor productColor = new ProductColor();
        Set<ProductColorName> productColorNames = createProductColorNames(adminBaseNameObject, productColor);
        productColor.setProductColorNames(productColorNames);
        productColorRepository.save(productColor);
    }

    @Override
    public void deleteProductColor(AdminBaseNameObject adminBaseNameObject) {
        productColorRepository.delete(adminBaseNameObject.getId());
    }

    @Override
    public void updateProductColor(AdminBaseNameObject adminBaseNameObject) {
        ProductColor productColor = productColorRepository.findOne(adminBaseNameObject.getId());
        Set<ProductColorName> productColorNames = productColor.getProductColorNames();
        findAndUpdateProductColorName(Locale.English, productColorNames, adminBaseNameObject.getNameEN(), productColor);
        findAndUpdateProductColorName(Locale.Russian, productColorNames, adminBaseNameObject.getNameRU(), productColor);
        findAndUpdateProductColorName(Locale.Ukraine, productColorNames, adminBaseNameObject.getNameUA(), productColor);
        productColor.setProductColorNames(productColorNames);
        productColorRepository.save(productColor);
    }

    private ProductColorName findAndUpdateProductColorName(Locale locale, Set<ProductColorName> productColorNames, String name, ProductColor productColor) {
        for (ProductColorName productColorName : productColorNames) {
            if(locale.equals(productColorName.getLocale())){
                productColorName.setName(name);
                return productColorName;
            }
        }
        ProductColorName productColorName = new ProductColorName(name,  locale, productColor);
        productColorNames.add(productColorName);
        return productColorName;
    }


    private Set<ProductColorName> createProductColorNames(AdminBaseNameObject adminBaseNameObject, ProductColor productColor){
        ProductColorName productColorNameEN = new ProductColorName(adminBaseNameObject.getNameEN(),  Locale.English, productColor);
        ProductColorName productColorNameRU = new ProductColorName(adminBaseNameObject.getNameRU(), Locale.Russian, productColor);
        ProductColorName productColorNameUA = new ProductColorName(adminBaseNameObject.getNameUA(), Locale.Ukraine, productColor);
        return new HashSet<>(Arrays.asList(productColorNameEN, productColorNameRU, productColorNameUA));
    }

}
