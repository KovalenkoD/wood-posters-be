package com.woodposters.repository;

import com.woodposters.entity.material.Material;
import org.springframework.data.repository.CrudRepository;

public interface MaterialRepository extends CrudRepository<Material,Long> {
}
