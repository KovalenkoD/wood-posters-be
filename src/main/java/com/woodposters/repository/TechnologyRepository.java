package com.woodposters.repository;

import com.woodposters.entity.productType.ProductType;
import com.woodposters.entity.technology.Technology;
import org.springframework.data.repository.CrudRepository;

public interface TechnologyRepository  extends CrudRepository<Technology,Long> {
}

