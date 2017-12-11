package com.woodposters.service.quote;

import com.woodposters.entity.product.Product;
import com.woodposters.entity.quote.SalesOrder;

public interface SalesOrderService {
     SalesOrder addOrderToSalesOrder(Product product, SalesOrder salesOrder, int count);

     SalesOrder changeCountOfItemsFromSalesOrder(Product product, SalesOrder salesOrder, int count);

     SalesOrder deleteOrdersFormSalesOrder(Product product, SalesOrder salesOrder);

     SalesOrder cleanSalesOrder(SalesOrder salesOrder);



}
