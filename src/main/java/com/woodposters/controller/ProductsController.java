package com.woodposters.controller;

import com.woodposters.beans.WizardState;
import com.woodposters.converters.ProductConverter;
import com.woodposters.entity.product.Product;
import com.woodposters.service.product.ProductService;
import com.woodposters.service.search.ProductSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("products")
public class ProductsController {

    @Autowired
    ProductService productService;

    @Autowired
    private ProductSearch productSearch;

    @Autowired
    private WizardState wizardState;

    @GetMapping("getAllProducts")
    public ResponseEntity<List> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List result = ProductConverter.convert(products, wizardState.getLocale());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("addProducts")
    public ResponseEntity<Void> addProducts() {
        productService.addProducts();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

/*    @PutMapping("updateProduct")
    public ResponseEntity<Product> updateArticle(@RequestBody Product product) {
        articleService.updateArticle(article);
        return new ResponseEntity<Article>(article, HttpStatus.OK);
    }
   */
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
}
