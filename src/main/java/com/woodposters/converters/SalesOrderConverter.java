package com.woodposters.converters;

import com.woodposters.beans.Locale;
import com.woodposters.entity.quote.CartItem;
import com.woodposters.entity.quote.Contact;
import com.woodposters.entity.quote.SalesOrder;

import java.util.*;

public class SalesOrderConverter  extends CommonConverter {

    public static Map<String, Object> convert(SalesOrder salesOrder, Locale locale) {
        Map<String, Object> result = new HashMap<>();
        result.put("count", salesOrder.getCount());
        result.put("fullPrice", salesOrder.getFullPrice());
   //     result.put("contact", convertContact(salesOrder.getContact()));

        result.put("cartItems", convertCartItems(salesOrder.getCartItems(), locale));
        return result;
    }

    private static Map<String, Object> convertContact(Contact contact){
        Map<String, Object> contactMap = new HashMap<>();

        contactMap.put("firstName", contact.getFirstName());
        contactMap.put("lastName", contact.getLastName());
        contactMap.put("comment", contact.getComment());
        contactMap.put("phone", contact.getPhone());
        contactMap.put("email", contact.getEmail());

        return contactMap;
    }

    public static List<Map<String, Object>> convertCartItems(Set<CartItem> cartItems, Locale locale) {
        List<Map<String, Object>> cartItemsResult = new ArrayList<>();
        cartItems.forEach(cartItem -> cartItemsResult.add(convertCartItem(cartItem, locale)));
        return cartItemsResult;
    }

    public static Map<String, Object> convertCartItem(CartItem cartItem, Locale locale) {
        Map<String, Object> cartItemResult = new HashMap<>();
        cartItemResult.put("count", cartItem.getCount());
        cartItemResult.put("calculatedPrice", cartItem.calculatedPrice());
        cartItemResult.put("product", ProductConverter.convert(cartItem.getProduct(), locale));
        return cartItemResult;
    }
}
