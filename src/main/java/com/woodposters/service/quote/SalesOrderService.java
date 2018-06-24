package com.woodposters.service.quote;

import com.woodposters.entity.product.Product;
import com.woodposters.entity.quote.SalesOrder;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface SalesOrderService {
     SalesOrder addOrderToSalesOrder(Product product, SalesOrder salesOrder, int count);

     SalesOrder changeCountOfItemsFromSalesOrder(Product product, SalesOrder salesOrder, int count);

     SalesOrder deleteOrdersFormSalesOrder(Product product, SalesOrder salesOrder);

     SalesOrder cleanSalesOrder(SalesOrder salesOrder);

     SalesOrder submitSalesOrder(SalesOrder salesOrder);

     SalesOrder changeSalesOrderStatus(long salesOrderId, short status);

     double recalculatePrice(SalesOrder salesOrder);

     @Secured({"ROLE_ADMIN"})
     List<SalesOrder> getSalesOrderByStatus(short status);



}
