package com.woodposters.service.search;

import com.woodposters.entity.product.Product;

import java.util.List;

public interface ProductSearch {
    List<Product> fullSearch(String searchTerm);
}
