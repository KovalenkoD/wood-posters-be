package com.woodposters.controller;

import com.woodposters.beans.WizardState;
import com.woodposters.converters.ProductConverter;
import com.woodposters.converters.SalesOrderConverter;
import com.woodposters.entity.product.Product;
import com.woodposters.entity.quote.SalesOrder;
import com.woodposters.repository.ProductRepository;
import com.woodposters.service.quote.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("quote")
public class QuoteController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SalesOrderService salesOrderService;

    @Autowired
    private WizardState wizardState;

    @GetMapping("addOrdersToSalesOrder/{id}/{count}")
    public ResponseEntity<Map> addOrdersToSalesOrder(@PathVariable("id") Long id, @PathVariable("count") int count) {
        Product product = productRepository.findOne(id);
        SalesOrder salesOrder = wizardState.getSalesOrder();
        salesOrder = salesOrderService.addOrderToSalesOrder(product, salesOrder, count);
        Map result = SalesOrderConverter.convert(salesOrder, wizardState.getLocale());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("getSalesOrderInfo")
    public ResponseEntity<Map> getSalesOrderInfo() {
        SalesOrder salesOrder = wizardState.getSalesOrder();
        Map result = SalesOrderConverter.convert(salesOrder, wizardState.getLocale());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("changeCountOfItemsFromSalesOrder/{id}/{count}")
    public ResponseEntity<Map> changeCountOfItemsFromSalesOrder(@PathVariable("id") Long id, @PathVariable("count") int count) {
        Product product = productRepository.findOne(id);
        SalesOrder salesOrder = wizardState.getSalesOrder();
        salesOrderService.changeCountOfItemsFromSalesOrder(product, salesOrder, count);
        Map result = SalesOrderConverter.convert(salesOrder, wizardState.getLocale());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("deleteOrdersFormSalesOrder/{id}")
    public ResponseEntity<Map> deleteOrdersFormSalesOrder(@PathVariable("id") Long id) {
        Product product = productRepository.findOne(id);
        SalesOrder salesOrder = wizardState.getSalesOrder();
        salesOrder = salesOrderService.deleteOrdersFormSalesOrder(product, salesOrder);
        Map result = SalesOrderConverter.convert(salesOrder, wizardState.getLocale());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("cleanSalesOrder")
    public ResponseEntity<Map> cleanSalesOrder() {
        SalesOrder salesOrder = wizardState.getSalesOrder();
        salesOrderService.cleanSalesOrder(salesOrder);
        Map result = SalesOrderConverter.convert(salesOrder, wizardState.getLocale());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}