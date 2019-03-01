package com.woodposters.service.mailContent;

import com.woodposters.beans.Locale;
import com.woodposters.converters.CommonConverter;
import com.woodposters.entity.quote.Contact;
import com.woodposters.entity.quote.SalesOrder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MailContentBuilderImpl implements MailContentBuilder {

    @Autowired
    private TemplateEngine templateEngine;

    public String build(SalesOrder salesOrder) {
        Context context = new Context();
        List<Map<String, Object>> orders = new ArrayList<>();
        salesOrder.getCartItems().forEach(cartItem -> {
            Map<String, Object> order = new HashMap<>();
            order.put("productName", CommonConverter.getStringFromLocaleNameObjects(cartItem.getProduct().getProductNames(), Locale.Russian) + " (арт.: " + cartItem.getProduct().getArticul() + String.format(" в количестве: %s единиц", cartItem.getCount()));
            order.put("totalPrice", cartItem.calculatedPrice() + " грн");
            orders.add(order);
        });

        context.setVariable("thankYou", "Спасибо за ваш заказ!");
        context.setVariable("totalPrice", salesOrder.getFullPrice() + " грн");
        context.setVariable("orders", orders);
        context.setVariable("weHope", String.format("Мы надеемся, что наши товары принесут вам много радости и позитива! <br/> Номер вашего заказа: №%s. <br/>" +
                "Вы можете связаться с нами по телефону +38 097 340 7233", salesOrder.getId()));

        return templateEngine.process("mailTemplate", context);
    }

    public String buildForPosters(SalesOrder salesOrder, Contact contact) {
        Context context = new Context();
        List<Map<String, Object>> orders = new ArrayList<>();
        salesOrder.getCartItems().forEach(cartItem -> {
            Map<String, Object> order = new HashMap<>();
            order.put("productName", CommonConverter.getStringFromLocaleNameObjects(cartItem.getProduct().getProductNames(), Locale.Russian) + " (арт.: " + cartItem.getProduct().getArticul() + String.format(") в количестве: %s единиц", cartItem.getCount()));
            order.put("totalPrice", cartItem.calculatedPrice() + " грн");
            orders.add(order);
        });

        context.setVariable("thankYou", "Типуля сделал заказ!");
        context.setVariable("totalPrice", salesOrder.getFullPrice() + " грн");
        context.setVariable("orders", orders);
        context.setVariable("weHope", String.format("Хорошо бы позвонить ему по телефону! <br/> Номер заказа: №%s. Телефон типули: %s. <br/>" +
                "Типочка зовут: %s" +
                "<br/> Почта: %s" +
                "<br/> Доставка: %s", salesOrder.getId(), contact.getPhone(), contact.getFirstName() + " " + contact.getLastName(), contact.getEmail(), getDeliveryString(contact.getDelivery())));

        return templateEngine.process("adminMailTemplate", context);
    }

    private String getDeliveryString(String delivery){
        if(StringUtils.isEmpty(delivery)){
            return delivery;
        } if(delivery.equals("post")){
            return "Новая Почта";
        } else if(delivery.equals("kremenchuk")){
            return "Самовывоз Кременчуг, ТРК Европа";
        } else {
            return delivery;
        }
    }
}
