package com.woodposters.controller;

import com.woodposters.beans.WizardState;
import com.woodposters.converters.ProductConverter;
import com.woodposters.converters.SalesOrderConverter;
import com.woodposters.entity.adminModel.AdminProduct;
import com.woodposters.entity.adminModel.AdminProductType;
import com.woodposters.entity.product.Product;
import com.woodposters.entity.quote.Contact;
import com.woodposters.entity.quote.DeliveryAddress;
import com.woodposters.entity.quote.SalesOrder;
import com.woodposters.entity.quote.SalesOrderIdAndStatus;
import com.woodposters.repository.ProductRepository;
import com.woodposters.service.quote.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

    @RequestMapping(value = "submitOrder", method = RequestMethod.POST)
    public ResponseEntity<Map> submitOrder(@RequestBody Contact contact) {
        SalesOrder salesOrder = wizardState.getSalesOrder();
        contact.setSalesOrder(salesOrder);
        salesOrder.setContact(contact);

        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setSalesOrder(salesOrder);
        salesOrder.setDeliveryAddress(deliveryAddress);
        salesOrder.setStatus((short) 0);
        salesOrderService.submitSalesOrder(salesOrder);
        salesOrder = new SalesOrder();
        wizardState.setSalesOrder(salesOrder);
        Map result = SalesOrderConverter.convert(salesOrder, wizardState.getLocale());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("getSalesOrdersByStatus/{status}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List> getSalesOrdersByStatus(@PathVariable("status") short status) {
        List result = new ArrayList();
        Iterable<SalesOrder> salesOrders = salesOrderService.getSalesOrderByStatus(status);
        salesOrders.forEach(salesOrder -> {
            salesOrder.setFullPrice(salesOrderService.recalculatePrice(salesOrder));
            result.add(SalesOrderConverter.convert(salesOrder, wizardState.getLocale()));
        });
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "changeSalesOrderStatus", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> changeSalesOrderStatus(@RequestBody SalesOrderIdAndStatus salesOrderIdAndStatus) {

       salesOrderService.changeSalesOrderStatus(salesOrderIdAndStatus.getSalesOrderId(), salesOrderIdAndStatus.getStatus());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
