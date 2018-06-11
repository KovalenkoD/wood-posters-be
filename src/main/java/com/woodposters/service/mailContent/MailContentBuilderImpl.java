package com.woodposters.service.mailContent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilderImpl implements MailContentBuilder {

    @Autowired
    private TemplateEngine templateEngine;

    public String build(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("thankYou", "Спасибо за ваш заказ!");
        context.setVariable("weHope", "Мы надеемся, что наши товари принесут вам много радости и позитива! <br/> Номер вашего заказа: №123123");

        return templateEngine.process("mailTemplate", context);
    }
}
