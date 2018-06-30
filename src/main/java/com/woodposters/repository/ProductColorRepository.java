package com.woodposters.repository;

import com.woodposters.entity.productColor.ProductColor;
import org.springframework.data.repository.CrudRepository;

/**
 *@author Dmitry Kovalenko
 */
public interface ProductColorRepository extends CrudRepository<ProductColor,Long> {
}

