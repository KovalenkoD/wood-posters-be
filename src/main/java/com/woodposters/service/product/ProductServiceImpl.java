package com.woodposters.service.product;

import com.woodposters.entity.category.Category;
import com.woodposters.entity.category.CategoryName;
import com.woodposters.entity.product.BundleProduct;
import com.woodposters.entity.product.Product;
import com.woodposters.entity.product.ProductName;
import com.woodposters.repository.CategoryRepository;
import com.woodposters.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getProductsByType(String type) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
       // Criteria criteria =
        return new ArrayList<>();
    }

    private Category createCategory(){
        Category category = new Category();
        CategoryName productNameEN = new CategoryName("humor", "en", category);
        CategoryName productNameRU = new CategoryName("Юмор", "ru", category);
        category.setCategoryNames(new HashSet<>(Arrays.asList(productNameEN, productNameRU)));
        categoryRepository.save(category);
        return category;
    }

    @Override
    public void addProducts() {
        Category category = createCategory();
        Product product1 = new Product();
        product1.setPrice(250);
        product1.setType("comedy");
        ProductName productNameTed = new ProductName("sdasdasd", "en", product1);
        product1.setProductNames(new HashSet<>(Arrays.asList(productNameTed)));
        product1.setCategories(new HashSet<>(Arrays.asList(category)));
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setPrice(250);
        product2.setType("comedy");
        ProductName productNameHonus = new ProductName("Honus", "ua", product2);
        product2.setProductNames(new HashSet<>(Arrays.asList(productNameHonus, productNameTed)));
        product2.setCategories(new HashSet<>(Arrays.asList(category)));
        productRepository.save(product2);

        BundleProduct bundleProduct = new BundleProduct();
        bundleProduct.setPrice(250);
        bundleProduct.setType("юмор");
        bundleProduct.setBundleImage("testImage");
        ProductName productNameBundle= new ProductName("Супер Бандл", "ru", bundleProduct);
        ProductName productNameBundle2= new ProductName("Super Bundle", "EN", bundleProduct);
        bundleProduct.setProductNames(new HashSet<>(Arrays.asList(productNameBundle, productNameBundle2)));
        bundleProduct.setCategories(new HashSet<>(Arrays.asList(category)));
        productRepository.save(bundleProduct);

        BundleProduct bundleProduct2 = new BundleProduct();
        bundleProduct2.setPrice(250);
        bundleProduct2.setType("юмор");
        bundleProduct2.setBundleImage("testImage2");
        ProductName productNameBundle4= new ProductName("Супер Бандл 2", "ru", bundleProduct2);
        ProductName productNameBundle5= new ProductName("Super Bundle 2", "EN", bundleProduct2);
        bundleProduct2.setProductNames(new HashSet<>(Arrays.asList(productNameBundle4, productNameBundle5)));
        bundleProduct2.setCategories(new HashSet<>(Arrays.asList(category)));
        productRepository.save(bundleProduct2);

    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productsList = new ArrayList<>();
        Iterable<Product> products = productRepository.findAll();
        Iterator<Product> iter = products.iterator();
        while(iter.hasNext()){
            Product product = iter.next();
            productsList.add(product);
        }
        return productsList;
    }
}
