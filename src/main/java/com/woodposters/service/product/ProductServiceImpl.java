package com.woodposters.service.product;

import com.google.common.collect.Sets;
import com.woodposters.beans.Locale;
import com.woodposters.entity.adminModel.AdminProduct;
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
        productTypes.add(createType("Газетницы и журнальницы", "Newspapers and magazines", "https://cs5.livemaster.ru/storage/0c/6d/558b473c32b11a8d43d762818esz--kantselyarskie-tovary-zhurnalnitsa-flowers.jpg"));
        productTypes.add(createType("Шкатулки", "Caskets", "https://cs5.livemaster.ru/storage/a5/2d/cf0256219fb82c87e33e633b602m--dlya-doma-i-interera-shkatulka-cacao.jpg"));
        productTypes.add(createType("Магниты на холодильник", "Fridge magnets", "http://indada.ru/system/images/7357/large/magnit-na-holodilnik-16.jpg"));
        productTypes.add(createType("Вечные календари", "Perpetual Calendars", "https://cs1.livemaster.ru/storage/3e/b7/0412be7fbec5e9a547e1c82f74xy--kantselyarskie-tovary-vechnyj-kalendar-sova.jpg"));
        productTypes.add(createType("Конфетницы и спецовницы", "Candy and spelled girls", "https://cs5.livemaster.ru/storage/40/ab/1e799a1da6fac8ce53e4a4cfe651--materialy-dlya-tvorchestva-konfetnitsa-zagotovka.jpg"));

        productTypes.add(createType("Настенное пано", "Wall Panorama", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTqGp-Z7LX8Yqe6MUCMvFwhmmcOgKHfU1u7tIEPD-UpF2kXc9A6"));

        return productTypes;
    }

    private ProductType createType(String russianName, String englishName, String image){

        return createType(russianName, englishName, image, (short) 1);
    }

    private ProductType createType(String russianName, String englishName, String image, short visible){
        ProductType productType = new ProductType();
        ProductTypeName productNameEN = new ProductTypeName(englishName,  Locale.English, productType);
        ProductTypeName productNameRU = new ProductTypeName(russianName, Locale.Russian, productType);
        productType.setProductTypeNames(new HashSet<>(Arrays.asList(productNameEN, productNameRU)));
        productType.setVisible(visible);
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

        ProductType poster = createType("Постеры", "Posters", "https://lovely-dom.ru/wp-content/uploads/2017/05/poster2.jpg");

        ProductType collection = createType("Коллекции", "Collections", "https://lovely-dom.ru/wp-content/uploads/2017/05/poster2.jpg", (short) 0);



        List<String> names = Arrays.asList("супер товар", "очень товар", "охуеть товар", "заебись товар", "пиздат товар", "необыкновене товар", "я в ахуе товар");
        List<String> images = Arrays.asList("http://omegatea.ru/img/big/522000.png", "http://лавкажеланий.рф/images/11shk.png", "http://img1.liveinternet.ru/images/attach/c/7/97/870/97870953_000.png");

        List<Product> products = new ArrayList<>();

        List<String> posterNames = Arrays.asList("Постер Кухня", "Постер Барселона", "Постер Париж", "Постер Гаваи", "Постер Санта Клаус", "Постер Симпсон", "Постер Милан");
        List<String> posterImages = Arrays.asList("https://dyn0.media.forbiddenplanet.com/products/2913599.jpg.square-true_maxheight-285_size-285.jpg"
                , "https://colapsar.ru/upload/resize_cache/iblock/a56/280_280_1/a56bbc83acd531f9a7024c87d515ac9f.jpg", "https://pp.userapi.com/c605119/v605119567/275c/AJzfpZ75AW4.jpg"
                , "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnHTO3eoAY228IZZQ--nrntzITuu-7fNtWZEyEiepF6n5068qh", "https://s-media-cache-ak0.pinimg.com/originals/4d/cb/26/4dcb26e96dc0ea0ad2df59b7470c13fe.jpg"
                , "https://avatars.mds.yandex.net/get-pdb/245485/667ad20e-7aac-43d6-b9b1-4e46db1a9568/s800", "https://yandex.ru/collections/card/59b244db2321f22ed0d623d8/");

        for(int i = 0 ; i< posterNames.size(); i++) {
            String name = posterNames.get(i);
            String image = posterImages.get(i);

            createProduct(name + " RU", name + " EN", image, (short) 1, poster, currentDate, technologies, materials, category, (short) 1, null);
        }

        Product product = null;
        for(ProductType productType1 : productTypes){
            for(int i = 0; i < names.size(); i ++){
                String name = names.get(i);
                for(String image : images){
                    if(i % 3 == 0){
                        product = createProduct(name + " RU", name + " EN", image, (short) 1, productType1, currentDate, technologies, materials, category, (short) 0, posterImages);
                    } else if(i%2 == 0){
                        product = createProduct(name + " RU", name + " EN", image, (short) 2, productType1, currentDate, technologies, materials, category, (short) 0, posterImages);
                    } else {
                        product = createProduct(name + " NP RU", name + "NP EN", image, (short) 0, productType1, currentDate, technologies, materials, category, (short) 0, posterImages);
                    }
                    if(products.size() < 3){
                        products.add(product);
                    }
                }
            }
            createBundleProduct("Бандл продукт RU", "Bundle Product EN", "https://walldeco.ua/img/for_page/poster5.jpg", (short) 0, collection, currentDate, technologies, materials, category, products, (short) 2);
            createBundleProduct("Бандл поп продукт RU", "Bundle POP Product EN", "https://walldeco.ua/img/for_page/poster5.jpg", (short) 1, collection, currentDate, technologies, materials, category, products, (short) 2);

        }
    }

    private Product createProduct(String russianName, String englishName, String image, short popular, ProductType productType, Date currentDate, Set<Technology> technologies, Set<Material> materials, Category category, short imagePresentation, List<String> images){
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
        product.setImagePresentation(imagePresentation);
        product.setMaterials(materials);
        product.setCreatedDate(currentDate);
        product.setImage(image);
        product.setPopular(popular);

        if(images != null){
            Set<ProductImage> productImages = new HashSet<>();
            images.forEach(imagePR -> productImages.add(new ProductImage(imagePR, product)));
            product.setImages(productImages);
        }

        productRepository.save(product);
        return product;
    }

    private void createBundleProduct(String russianName, String englishName, String image, short popular, ProductType productType, Date currentDate, Set<Technology> technologies, Set<Material> materials, Category category, List<Product> products, short imagePresentation){
        BundleProduct bundleProduct = new BundleProduct();
        bundleProduct.setPrice(250);
        bundleProduct.setSize("145 x 145 x 70");
        bundleProduct.setTechnologies(technologies);
        ProductName productNameRU = new ProductName(russianName, Locale.Russian, bundleProduct);
        ProductName productNameEN= new ProductName(englishName, Locale.English, bundleProduct);
        bundleProduct.setProductNames(new HashSet<>(Arrays.asList(productNameRU, productNameEN)));
        ProductDescription productDescriptionRU= new ProductDescription("Какой то дескрипшен",  Locale.Russian, bundleProduct);
        ProductDescription productDescriptionEN= new ProductDescription("Some description Informatoim",  Locale.English, bundleProduct);
        bundleProduct.setProductDescriptions(new HashSet<>(Arrays.asList(productDescriptionRU, productDescriptionEN)));
        bundleProduct.setCategories(new HashSet<>(Arrays.asList(category)));
        bundleProduct.setProductType(productType);
        bundleProduct.setMaterials(materials);
        bundleProduct.setCreatedDate(currentDate);
        bundleProduct.setBundleImage(image);
        bundleProduct.setImage(image);
        bundleProduct.setImagePresentation(imagePresentation);

        Set<BundleChildProduct> bundleChildProducts = new HashSet<>();
        for(int i = 0; i < products.size(); i++){
            Product product = products.get(i);
            BundleChildProduct bundleChildProduct = new BundleChildProduct();
            bundleChildProduct.setBundleProduct(bundleProduct);
            bundleChildProduct.setProduct(product);
            bundleChildProduct.setX_coordinate(15*i);
            bundleChildProduct.setY_coordinate(15*i);
            bundleChildProducts.add(bundleChildProduct);
        }

        bundleProduct.setBundleChildProducts(bundleChildProducts);

        bundleProduct.setPopular(popular);




        productRepository.save(bundleProduct);
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

    @Override
    public List<BundleProduct> getMostPopularBundleProducts(final String discriminator, short popular) {
        List<BundleProduct> list = entityManager.createQuery("SELECT product FROM BundleProduct product WHERE discriminator=? AND popular=?")
                .setParameter(1, discriminator).setParameter(2, popular).getResultList();
        return list;
    }

    @Override
    public List<BundleProduct> getAllBundles() {
        List<BundleProduct> list = entityManager.createQuery("SELECT product FROM BundleProduct product WHERE discriminator=?")
                .setParameter(1, "BP").getResultList();
        return list;
    }

    @Override
    public List<Product> findRelatedProducts(long id) {
        Product product = productRepository.findOne(id);
        List<Product> list = entityManager.createQuery("SELECT product FROM Product product WHERE product.productType.id=?")
                .setParameter(1, product.getProductType().getId()).getResultList();
        return list;
    }


    @Override
    public void createProduct(AdminProduct adminProduct) {
        Set<Category> categories = Sets.newHashSet(categoryRepository.findAll(adminProduct.getCategoryIDs()));
        Set<Material> materials = Sets.newHashSet(materialRepository.findAll(adminProduct.getMaterialIDs()));
        Set<Technology> technologies = Sets.newHashSet(technologyRepository.findAll(adminProduct.getTechnologyIDs()));

        ProductType productType = productTypeRepository.findOne(adminProduct.getProductTypeID());
        createProduct(adminProduct.getRussianName(), adminProduct.getEnglishName(), adminProduct.getUkrainianName(),
                adminProduct.getPrice(), adminProduct.isBundle(), adminProduct.getSize(),technologies,
                adminProduct.getRussianDescription(), adminProduct.getEnglishDescription(), adminProduct.getUkrainianDescription(),
                categories, productType, materials, adminProduct.getPopular(),
                adminProduct.getImagePresentation(), adminProduct.getImages(), adminProduct.getImage());
    }

    private Product createProduct(String russianName, String englishName, String ukrainianName,
                                  double price, boolean isBundle, String size, Set<Technology> technologies,
                                  String russianDescription, String englishDescription, String ukrainianDescription,
                                  Set<Category> categories, ProductType productType, Set<Material> materials, short popular,
                                  short imagePresentation, Set<String> images, String image){

        Product product = isBundle ? new BundleProduct() : new Product();
        Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        product.setPrice(price);
        product.setSize(size);
        product.setTechnologies(technologies);
        createProductName(russianName, englishName, ukrainianName, product);
        createProductDescription(russianDescription, englishDescription, ukrainianDescription, product);
        product.setCategories(categories);
        product.setProductType(productType);
        product.setImagePresentation(imagePresentation);
        product.setMaterials(materials);
        product.setCreatedDate(currentDate);
        product.setImage(image);
        product.setPopular(popular);

        if(images != null){
            Set<ProductImage> productImages = new HashSet<>();
            images.forEach(imagePR -> productImages.add(new ProductImage(imagePR, product)));
            product.setImages(productImages);
        }

        productRepository.save(product);

        return product;
    }

    private void createProductName(String russianName, String englishName, String ukrainianName, Product product){
        ProductName productNameRU = new ProductName(russianName, Locale.Russian, product);
        ProductName productNameEN= new ProductName(englishName, Locale.English, product);
        ProductName productNameUA= new ProductName(ukrainianName, Locale.Ukraine, product);
        product.setProductNames(new HashSet<>(Arrays.asList(productNameRU, productNameEN, productNameUA)));
    }

    private void createProductDescription(String russianDescription, String englishDescription, String ukrainianDescription, Product product){
        ProductDescription productDescriptionRU= new ProductDescription(russianDescription,  Locale.Russian, product);
        ProductDescription productDescriptionEN= new ProductDescription(englishDescription,  Locale.English, product);
        ProductDescription productDescriptionUA= new ProductDescription(ukrainianDescription,  Locale.Ukraine, product);
        product.setProductDescriptions(new HashSet<>(Arrays.asList(productDescriptionRU, productDescriptionEN, productDescriptionUA)));
    }

}
