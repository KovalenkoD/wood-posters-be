package com.woodposters.repository;

import com.woodposters.entity.quote.SalesOrder;
import org.springframework.data.repository.CrudRepository;

public interface SalesOrderRepository extends CrudRepository<SalesOrder,Long> {
}
