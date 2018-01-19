package com.woodposters.service.quote;

import com.woodposters.entity.product.Product;
import com.woodposters.entity.quote.CartItem;
import com.woodposters.entity.quote.SalesOrder;
import com.woodposters.repository.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class SalesOrderServiceImpl implements SalesOrderService {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SalesOrder addOrderToSalesOrder(Product product, SalesOrder salesOrder, int count) {
        Set<CartItem> cartItems = salesOrder.getCartItems();
        CartItem cartItem = null;
        if(cartItems != null) {
            cartItem = findCartItemInSalesOrder(product, salesOrder);
        } else {
            cartItems = new HashSet<>();
            salesOrder.setCartItems(cartItems);
        }

        if(cartItem != null){
            cartItem.setCount(cartItem.getCount() + count);
        } else {
            cartItem = new CartItem(count, product, salesOrder);
            cartItems.add(cartItem);
        }

        recalculateSalesOrderParameters(salesOrder);

        return salesOrder;
    }

    @Override
    public SalesOrder changeCountOfItemsFromSalesOrder(Product product, SalesOrder salesOrder, int count) {
        Set<CartItem> cartItems = salesOrder.getCartItems();
        if(cartItems != null) {
            CartItem cartItem = findCartItemInSalesOrder(product, salesOrder);
            cartItem.setCount(count);
        }

        recalculateSalesOrderParameters(salesOrder);

        return salesOrder;
    }

    @Override
    public SalesOrder deleteOrdersFormSalesOrder(Product product, SalesOrder salesOrder) {
        Set<CartItem> cartItems = salesOrder.getCartItems();
        if(cartItems != null) {
            CartItem cartItem = findCartItemInSalesOrder(product, salesOrder);
            cartItems.remove(cartItem);
        }

        recalculateSalesOrderParameters(salesOrder);

        return salesOrder;
    }

    @Override
    public SalesOrder cleanSalesOrder(SalesOrder salesOrder) {
        salesOrder.setCount(0);
        salesOrder.setFullPrice(0);
        salesOrder.getCartItems().clear();
        return salesOrder;
    }

    @Override
    public SalesOrder submitSalesOrder(SalesOrder salesOrder) {
        return salesOrderRepository.save(salesOrder);
    }

    private void recalculateSalesOrderParameters(SalesOrder salesOrder){
        double recalculatedFullPrice = recalculatePrice(salesOrder);
        salesOrder.setFullPrice(recalculatedFullPrice);

        int countItems = recalculateCount(salesOrder);
        salesOrder.setCount(countItems);
    }

    private CartItem findCartItemInSalesOrder(Product product, SalesOrder salesOrder){
        for (CartItem cartItem : salesOrder.getCartItems()) {
            if(product.getId() == cartItem.getProductId()){
               return cartItem;
            }
        }
        return null;
    }

    private int recalculateCount(SalesOrder salesOrder){
        int count = 0;
        for (CartItem cartItem : salesOrder.getCartItems()){
            count += cartItem.getCount();
        }
        return count;
    }

    private double recalculatePrice(SalesOrder salesOrder){
        double fullPrice = 0;
        for (CartItem cartItem : salesOrder.getCartItems()){
            fullPrice += cartItem.calculatedPrice();
        }
        return fullPrice;
    }

    public List<SalesOrder> getSalesOrderByStatus(short status){
        List<SalesOrder> list = entityManager.createQuery("SELECT salesOrder FROM SalesOrder salesOrder WHERE status=?")
                .setParameter(1, status).getResultList();
        return list;
    }

}
