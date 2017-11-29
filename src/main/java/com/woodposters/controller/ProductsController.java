package com.woodposters.controller;

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

    @GetMapping("getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
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
    public ResponseEntity<List<Product>> getProductByFullSearchSearch(@PathVariable("search") String search) {
        List<Product> searchResults = null;
        try {
            searchResults = productSearch.fullSearch(search);

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return new ResponseEntity<List<Product>>(searchResults, HttpStatus.OK);
    }
}
