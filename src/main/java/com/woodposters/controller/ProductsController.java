package com.woodposters.controller;

import com.woodposters.beans.Locale;
import com.woodposters.beans.WizardState;
import com.woodposters.converters.BundleConverter;
import com.woodposters.converters.CommonConverter;
import com.woodposters.converters.ProductConverter;
import com.woodposters.entity.adminModel.AdminProduct;
import com.woodposters.entity.adminModel.AdminProductType;
import com.woodposters.entity.category.Category;
import com.woodposters.entity.product.BundleProduct;
import com.woodposters.entity.product.Product;
import com.woodposters.repository.ProductRepository;
import com.woodposters.service.product.ProductService;
import com.woodposters.service.search.ProductSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("products")
public class ProductsController {

    @Autowired
    ProductService productService;

    @Autowired
    private ProductSearch productSearch;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WizardState wizardState;


    @GetMapping("addProducts")
    public ResponseEntity<Void> addProducts() {
        productService.addProducts();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("searchProduct/{search}")
    public ResponseEntity<List> getProductByFullSearchSearch(@PathVariable("search") String search) {
        List<Product> searchResults = null;
        try {
            searchResults = productSearch.fullSearch(search);
            System.out.println(wizardState.getLocale());
        } catch (Exception ex) {
            System.out.println(ex);
        }
        List result = ProductConverter.convert(searchResults, wizardState.getLocale());
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("getProductById/{id}")
    public ResponseEntity<Map> getProductsByCategory(@PathVariable("id") Long id) {
        Product product = productRepository.findOne(id);
        Map result = ProductConverter.convert(product, wizardState.getLocale());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("getAdminProductById/{id}")
    public ResponseEntity<AdminProduct> getAdminProductById(@PathVariable("id") Long id) {
        Product product = productRepository.findOne(id);
        AdminProduct adminProduct = new AdminProduct(product.getId(),
                CommonConverter.getStringFromLocaleNameObjects(product.getProductNames(), Locale.Russian),
                CommonConverter.getStringFromLocaleNameObjects(product.getProductNames(), Locale.English),
                CommonConverter.getStringFromLocaleNameObjects(product.getProductNames(), Locale.Ukraine),
                product.getPrice(),
                false,
                product.getSize(),
                CommonConverter.convertTechnologySetToIntIDs(product.getTechnologies()),
                CommonConverter.getStringFromLocaleDescriptionObjects(product.getProductDescriptions(), Locale.Russian),
                CommonConverter.getStringFromLocaleDescriptionObjects(product.getProductDescriptions(), Locale.English),
                CommonConverter.getStringFromLocaleDescriptionObjects(product.getProductDescriptions(), Locale.Ukraine),
                CommonConverter.convertCategoriesSetToIntIDs(product.getCategories()),
                product.getProductType().getId(),
                CommonConverter.convertMaterialsSetToIntIDs(product.getMaterials()),
                product.getPopular(),
                product.getImagePresentation(),
                CommonConverter.convertProductImagesToImages(product.getImages()),
                product.getImage(),
                product.getBackground(),
                product.getArticul()
                );
        return new ResponseEntity<>(adminProduct, HttpStatus.OK);


    }

    @GetMapping("getMostPopularProducts/{discriminator}/{popular}")
    public ResponseEntity<List> getMostPopularProducts(@PathVariable("discriminator") String discriminator, @PathVariable("popular") short popular ) {
        List<Product> products = productService.getMostPopularProducts(discriminator, popular);
        List result = ProductConverter.convert(products, wizardState.getLocale());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("getMostPopularBundle/{discriminator}/{popular}")
    public ResponseEntity<List> getMostPopularBundle(@PathVariable("discriminator") String discriminator, @PathVariable("popular") short popular ) {
        List<BundleProduct> products = productService.getMostPopularBundleProducts(discriminator, popular);
        List result = BundleConverter.convertBundles(products, wizardState.getLocale());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("getAllBundles")
    public ResponseEntity<List> getAllBundles() {
        List<BundleProduct> products = productService.getAllBundles();
        List result = BundleConverter.convertBundles(products, wizardState.getLocale());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("getRelatedProducts/{id}")
    public ResponseEntity<List> getMostPopularProducts(@PathVariable("id") long id) {
        List<Product> products = productService.findRelatedProducts(id);
        List result = ProductConverter.convert(products, wizardState.getLocale());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> create(@RequestBody AdminProduct adminProduct) {
        productService.createProduct(adminProduct);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> update(@RequestBody AdminProduct adminProduct) {
        productService.updateProduct(adminProduct);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "createBundle", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> createBundle(@RequestBody AdminProduct adminProduct) {
        productService.createBundle(adminProduct);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
