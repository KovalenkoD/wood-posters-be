package com.woodposters.repository;

import com.woodposters.entity.category.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}

