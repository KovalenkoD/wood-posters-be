package com.woodposters.repository;

import com.woodposters.entity.product.ProductName;
import org.springframework.data.repository.CrudRepository;

public interface ProductNameRepository extends CrudRepository<ProductName,Long> {
}

