package com.woodposters.service.product;

import com.woodposters.beans.Locale;
import com.woodposters.entity.category.Category;
import com.woodposters.entity.category.CategoryName;
import com.woodposters.entity.material.Material;
import com.woodposters.entity.material.MaterialName;
import com.woodposters.entity.product.BundleProduct;
import com.woodposters.entity.product.Product;
import com.woodposters.entity.product.ProductDescription;
import com.woodposters.entity.product.ProductName;
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
import java.util.*;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private MaterialRepository materialRepository;


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
        CategoryName productNameEN = new CategoryName("humor",  Locale.English, category);
        CategoryName productNameRU = new CategoryName("Юмор",  Locale.Russian, category);
        category.setCategoryNames(new HashSet<>(Arrays.asList(productNameEN, productNameRU)));
        categoryRepository.save(category);
        return category;
    }

    private ProductType createType(){
        createType("Постеры", "Posters");
        createType("Газетницы и журнальницы", "Newspapers and magazines");
        createType("Шкатулки", "Caskets");
        createType("Магниты на холодильник", "Fridge magnets");
        createType("Вечные календари", "Perpetual Calendars");
        createType("Конфетницы и спецовницы", "Candy and spelled girls");

        ProductType productType = createType("Настенное пано", "Wall Panorama");

        return productType;
    }

    private ProductType createType(String russianName, String englishName){
        ProductType productType = new ProductType();
        ProductTypeName productNameEN = new ProductTypeName(englishName,  Locale.English, productType);
        ProductTypeName productNameRU = new ProductTypeName(russianName, Locale.Russian, productType);
        productType.setProductTypeNames(new HashSet<>(Arrays.asList(productNameEN, productNameRU)));
        productTypeRepository.save(productType);
        return productType;
    }

    private Set<Material> createMaterial(){
        Set<Material> materials = new HashSet<Material>();
        materials.add(createMaterial("МДФ", "MDF"));
        materials.add(createMaterial("Дерево", "Tree"));
        createMaterial("Дуб", "Oak");

        Material material = createMaterial("Красное дерево", "Red tree");
        materials.add(material);
        return materials;
    }

    private Material createMaterial(String russianName, String englishName){
        Material material = new Material();
        MaterialName materialNameEN = new MaterialName(englishName,  Locale.English, material);
        MaterialName materialNameRU = new MaterialName(russianName, Locale.Russian, material);
        material.setMaterialNames(new HashSet<>(Arrays.asList(materialNameEN, materialNameRU)));
        materialRepository.save(material);
        return material;
    }



    private Set<Technology> createTechnologies(){
        Technology technology = new Technology();
        TechnologyName technologyEN = new TechnologyName("dekypaj",  Locale.English, technology);
        TechnologyName technologyRU = new TechnologyName("Декупаж", Locale.Russian, technology);
        technology.setTechnologyNames(new HashSet<>(Arrays.asList(technologyEN, technologyRU)));
        technologyRepository.save(technology);

        Technology technology2 = new Technology();
        TechnologyName technology2EN = new TechnologyName("old",  Locale.English, technology2);
        TechnologyName technology2RU = new TechnologyName("Состаривание", Locale.Russian, technology2);
        technology2.setTechnologyNames(new HashSet<>(Arrays.asList(technology2EN, technology2RU)));
        technologyRepository.save(technology2);

        Set<Technology> technologies = new HashSet<>(Arrays.asList(technology, technology2));
        return technologies;
    }

    @Override
    public void addProducts() {
        Category category = createCategory();
        Set<Technology> technologies = createTechnologies();
        Set<Material> materials = createMaterial();
        ProductType productType = createType();
        Product product1 = new Product();
        product1.setPrice(250);
        product1.setSize("145 x 145 x 70");
        product1.setTechnologies(technologies);
        ProductName productNameTed = new ProductName("sdasdasd",  Locale.English, product1);
        ProductName productNameTed2 = new ProductName("Шкатулка Охуенная",  Locale.Russian, product1);

        product1.setProductNames(new HashSet<>(Arrays.asList(productNameTed, productNameTed2)));
        ProductDescription productDescription = new ProductDescription("Some description Informatoim",  Locale.English, product1);
        product1.setProductDescriptions(new HashSet<>(Arrays.asList(productDescription)));
        product1.setCategories(new HashSet<>(Arrays.asList(category)));
        product1.setProductTypes(new HashSet<>(Arrays.asList(productType)));
        product1.setMaterials(materials);

        productRepository.save(product1);

        Product product2 = new Product();
        product2.setPrice(250);
        product2.setSize("145 x 145 x 70");
        product2.setTechnologies(technologies);
        ProductName productNameHonus = new ProductName("Honus", Locale.Russian, product2);
        product2.setProductNames(new HashSet<>(Arrays.asList(productNameHonus, productNameTed)));
        ProductDescription productDescription2 = new ProductDescription("Some description Informatoim",  Locale.English, product2);
        product2.setProductDescriptions(new HashSet<>(Arrays.asList(productDescription2)));
        product2.setCategories(new HashSet<>(Arrays.asList(category)));
        product2.setProductTypes(new HashSet<>(Arrays.asList(productType)));
        product2.setMaterials(materials);

        productRepository.save(product2);

        BundleProduct bundleProduct = new BundleProduct();
        bundleProduct.setPrice(250);
        bundleProduct.setBundleImage("testImage");
        bundleProduct.setSize("145 x 145 x 70");
        bundleProduct.setTechnologies(technologies);
        ProductName productNameBundle= new ProductName("Супер Бандл", Locale.Russian, bundleProduct);
        ProductName productNameBundle2= new ProductName("Super Bundle", Locale.English, bundleProduct);
        bundleProduct.setProductNames(new HashSet<>(Arrays.asList(productNameBundle, productNameBundle2)));
        ProductDescription productDescriptionBundle2 = new ProductDescription("Some description Informatoim",  Locale.English, bundleProduct);
        bundleProduct.setProductDescriptions(new HashSet<>(Arrays.asList(productDescriptionBundle2)));
        bundleProduct.setCategories(new HashSet<>(Arrays.asList(category)));
        bundleProduct.setProductTypes(new HashSet<>(Arrays.asList(productType)));
        bundleProduct.setMaterials(materials);

        productRepository.save(bundleProduct);

        BundleProduct bundleProduct2 = new BundleProduct();
        bundleProduct2.setPrice(250);
        bundleProduct2.setSize("145 x 145 x 70");
        bundleProduct2.setBundleImage("testImage2");
        bundleProduct2.setTechnologies(technologies);
        ProductName productNameBundle4= new ProductName("Супер Бандл 2", Locale.Russian, bundleProduct2);
        ProductName productNameBundle5= new ProductName("Super Bundle 2", Locale.English, bundleProduct2);
        bundleProduct2.setProductNames(new HashSet<>(Arrays.asList(productNameBundle4, productNameBundle5)));
        ProductDescription productDescriptionBundle4 = new ProductDescription("Some description Informatoim",  Locale.English, bundleProduct2);
        bundleProduct2.setProductDescriptions(new HashSet<>(Arrays.asList(productDescriptionBundle4)));
        bundleProduct2.setCategories(new HashSet<>(Arrays.asList(category)));
        bundleProduct2.setProductTypes(new HashSet<>(Arrays.asList(productType)));
        bundleProduct2.setMaterials(materials);

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
