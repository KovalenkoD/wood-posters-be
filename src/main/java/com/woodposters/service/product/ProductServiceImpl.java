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
import java.sql.Date;
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

    private List<ProductType> createType(){
        List<ProductType> productTypes = new ArrayList<>();
        productTypes.add(createType("Постеры", "Posters", "https://lovely-dom.ru/wp-content/uploads/2017/05/poster2.jpg"));
        productTypes.add(createType("Газетницы и журнальницы", "Newspapers and magazines", "https://cs5.livemaster.ru/storage/0c/6d/558b473c32b11a8d43d762818esz--kantselyarskie-tovary-zhurnalnitsa-flowers.jpg"));
        productTypes.add(createType("Шкатулки", "Caskets", "https://cs5.livemaster.ru/storage/a5/2d/cf0256219fb82c87e33e633b602m--dlya-doma-i-interera-shkatulka-cacao.jpg"));
        productTypes.add(createType("Магниты на холодильник", "Fridge magnets", "http://indada.ru/system/images/7357/large/magnit-na-holodilnik-16.jpg"));
        productTypes.add(createType("Вечные календари", "Perpetual Calendars", "https://cs1.livemaster.ru/storage/3e/b7/0412be7fbec5e9a547e1c82f74xy--kantselyarskie-tovary-vechnyj-kalendar-sova.jpg"));
        productTypes.add(createType("Конфетницы и спецовницы", "Candy and spelled girls", "https://cs5.livemaster.ru/storage/40/ab/1e799a1da6fac8ce53e4a4cfe651--materialy-dlya-tvorchestva-konfetnitsa-zagotovka.jpg"));

        productTypes.add(createType("Настенное пано", "Wall Panorama", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTqGp-Z7LX8Yqe6MUCMvFwhmmcOgKHfU1u7tIEPD-UpF2kXc9A6"));

        return productTypes;
    }

    private ProductType createType(String russianName, String englishName, String image){
        ProductType productType = new ProductType();
        ProductTypeName productNameEN = new ProductTypeName(englishName,  Locale.English, productType);
        ProductTypeName productNameRU = new ProductTypeName(russianName, Locale.Russian, productType);
        productType.setProductTypeNames(new HashSet<>(Arrays.asList(productNameEN, productNameRU)));
        productType.setImage(image);
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
        Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Category category = createCategory();
        Set<Technology> technologies = createTechnologies();
        Set<Material> materials = createMaterial();

       List<ProductType> productTypes = createType();
      /*
        BundleProduct bundleProduct = new BundleProduct();
        bundleProduct.setPrice(250);
        bundleProduct.setBundleImage("testImage");
        bundleProduct.setSize("145 x 145 x 70");
        bundleProduct.setTechnologies(technologies);
        bundleProduct.setPopular((short) 1);
        ProductName productNameBundle= new ProductName("Супер Бандл", Locale.Russian, bundleProduct);
        ProductName productNameBundle2= new ProductName("Super Bundle", Locale.English, bundleProduct);
        bundleProduct.setProductNames(new HashSet<>(Arrays.asList(productNameBundle, productNameBundle2)));
        ProductDescription productDescriptionBundle2 = new ProductDescription("Some description Informatoim",  Locale.English, bundleProduct);
        bundleProduct.setProductDescriptions(new HashSet<>(Arrays.asList(productDescriptionBundle2)));
        bundleProduct.setCategories(new HashSet<>(Arrays.asList(category)));
        bundleProduct.setProductTypes(new HashSet<>(Arrays.asList(productType)));
        bundleProduct.setMaterials(materials);
        bundleProduct.setCreatedDate(currentDate);

        productRepository.save(bundleProduct);
*/

        List<String> names = Arrays.asList("супер товар", "очень товар", "охуеть товар", "заебись товар", "пиздат товар", "необыкновене товар", "я в ахуе товар");
        List<String> images = Arrays.asList("http://omegatea.ru/img/big/522000.png", "http://лавкажеланий.рф/images/11shk.png", "http://img1.liveinternet.ru/images/attach/c/7/97/870/97870953_000.png");

        for(ProductType productType1 : productTypes){
            for(int i = 0; i < names.size(); i ++){
                String name = names.get(i);
                for(String image : images){
                    if(i % 3 == 0){
                        createProduct(name + " RU", name + " EN", image, (short) 1, productType1, currentDate, technologies, materials, category);
                    } else {
                        createProduct(name + " RU", name + " EN", image, (short) 2, productType1, currentDate, technologies, materials, category);
                    }
                    createProduct(name + " NP RU", name + "NP EN", image, (short) 0, productType1, currentDate, technologies, materials, category);

                }
            }

        }
    }

    private void createProduct(String russianName, String englishName, String image, short popular, ProductType productType, Date currentDate, Set<Technology> technologies, Set<Material> materials, Category category){
        Product product = new Product();
        product.setPrice(250);
        product.setSize("145 x 145 x 70");
        product.setTechnologies(technologies);
        ProductName productNameRU = new ProductName(russianName, Locale.Russian, product);
        ProductName productNameEN= new ProductName(englishName, Locale.English, product);
        product.setProductNames(new HashSet<>(Arrays.asList(productNameRU, productNameEN)));
        ProductDescription productDescriptionRU= new ProductDescription("Какой то дескрипшен",  Locale.Russian, product);
        ProductDescription productDescriptionEN= new ProductDescription("Some description Informatoim",  Locale.English, product);
        product.setProductDescriptions(new HashSet<>(Arrays.asList(productDescriptionRU, productDescriptionEN)));
        product.setCategories(new HashSet<>(Arrays.asList(category)));
        product.setProductType(productType);
        product.setMaterials(materials);
        product.setCreatedDate(currentDate);
        product.setImage(image);
        product.setPopular(popular);

        productRepository.save(product);
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

    @Override
    public List<Product> getMostPopularProducts(final String discriminator, short popular) {
        List<Product> list = entityManager.createQuery("SELECT product FROM Product product WHERE discriminator=? AND popular=?")
                .setParameter(1, discriminator).setParameter(2, popular).getResultList();
        return list;
    }


}
