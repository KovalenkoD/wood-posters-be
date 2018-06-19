package com.woodposters.service.mailContent;

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

    public String build(String message) {
        Context context = new Context();
        List<Map<String, Object>> orders = new ArrayList<>();
        Map<String, Object> order1 = new HashMap<>();
        order1.put("productName", "Супер постер");
        order1.put("productCount", "3");
        order1.put("totalPrice", "750 грн");
        Map<String, Object> order2 = new HashMap<>();
        order2.put("productName", "Супер постер 12");
        order2.put("productCount", "3");
        order2.put("totalPrice", "750 грн");
        Map<String, Object> order4 = new HashMap<>();
        order4.put("productName", "Супер постер 2");
        order4.put("productCount", "2");
        order4.put("totalPrice", "750 грн");

        orders.add(order1);
        orders.add(order2);
        orders.add(order4);


        context.setVariable("message", message);
        context.setVariable("thankYou", "Спасибо за ваш заказ!");
        context.setVariable("orders", orders);
        context.setVariable("weHope", "Мы надеемся, что наши товари принесут вам много радости и позитива! <br/> Номер вашего заказа: №123123");

        return templateEngine.process("mailTemplate", context);
    }
}
