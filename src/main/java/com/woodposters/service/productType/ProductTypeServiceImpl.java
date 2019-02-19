package com.woodposters.service.productType;

import com.woodposters.beans.Locale;
import com.woodposters.converters.ProductConverter;
import com.woodposters.entity.adminModel.AdminProductType;
import com.woodposters.entity.product.Product;
import com.woodposters.entity.productType.ProductType;
import com.woodposters.entity.productType.ProductTypeName;
import com.woodposters.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    @Cacheable(value="products", key="#id.toString()+#locale")
    public List getProductsByProductType(Long id, Locale locale) {
        ProductType category = findProductType(id);
        Set<Product> products = category.getProducts();
        List result = ProductConverter.lightConvert(products, locale);
        return result;
    }

    @Override
    public void createProductType(AdminProductType adminProductType) {
        createProductType(adminProductType.getNameRU(), adminProductType.getNameEN(), adminProductType.getNameUA(), adminProductType.getImageURL(), adminProductType.getBackground(), adminProductType.getVisible());
    }

    @Override
    public void updateProductType(AdminProductType adminProductType) {
        ProductType productType = productTypeRepository.findOne(adminProductType.getId());
        Set<ProductTypeName> productTypeNames = productType.getProductTypeNames();

        productType.setImage(adminProductType.getImageURL());

        findAndUpdateProductType(Locale.English, productTypeNames, adminProductType.getNameEN(), productType);
        findAndUpdateProductType(Locale.Russian, productTypeNames, adminProductType.getNameRU(), productType);
        findAndUpdateProductType(Locale.Ukraine, productTypeNames, adminProductType.getNameUA(), productType);

        productType.setVisible(adminProductType.getVisible());
        productType.setBackground(adminProductType.getBackground());

        productTypeRepository.save(productType);
    }

    @Override
    public void deleteProductType(AdminProductType adminProductType) {
        productTypeRepository.delete(adminProductType.getId());
    }

    private ProductTypeName findAndUpdateProductType(Locale locale, Set<ProductTypeName> productTypeNames, String name, ProductType productType) {
        for (ProductTypeName productTypeName : productTypeNames) {
            if(locale.equals(productTypeName.getLocale())){
                productTypeName.setName(name);
                return productTypeName;
            }
        }
        ProductTypeName productName = new ProductTypeName(name,  locale, productType);
        productTypeNames.add(productName);
        return productName;
    }


    private ProductType createProductType(String russianName, String englishName, String ukrainianName, String image, String background,  short visible){
        ProductType productType = new ProductType();
        ProductTypeName productNameEN = new ProductTypeName(englishName,  Locale.English, productType);
        ProductTypeName productNameRU = new ProductTypeName(russianName, Locale.Russian, productType);
        ProductTypeName productNameUA = new ProductTypeName(ukrainianName, Locale.Ukraine, productType);

        productType.setProductTypeNames(new HashSet<>(Arrays.asList(productNameEN, productNameRU, productNameUA)));
        productType.setVisible(visible);
        productType.setImage(image);
        productType.setBackground(background);
        productTypeRepository.save(productType);
        return productType;
    }
}
