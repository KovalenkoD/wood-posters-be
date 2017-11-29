package com.woodposters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("email")
public class EmailTriggerController {

    @Autowired
    MailSender mailSender;

    @RequestMapping(path = "/trigger", method = RequestMethod.GET)
    public String triggerEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Hello from Spring Boot Application");
        message.setSubject("Hello");
        message.setTo("dmitry.sergKovalenko@gmail.com");
        message.setFrom("dmitry.sergKovalenko@gmail.com");
        try {
            mailSender.send(message);
            return "{\"message\": \"OK\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"message\": \"Error\"}";
        }
    }

}