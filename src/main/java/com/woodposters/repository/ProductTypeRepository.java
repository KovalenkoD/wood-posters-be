package com.woodposters.repository;

import com.woodposters.entity.productType.ProductType;
import org.springframework.data.repository.CrudRepository;

public interface ProductTypeRepository extends CrudRepository<ProductType,Long> {
}

