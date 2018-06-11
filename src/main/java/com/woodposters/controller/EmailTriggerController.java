package com.woodposters.controller;

import com.woodposters.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("email")
public class EmailTriggerController {



    @Autowired
    private EmailService emailService;

    @RequestMapping(path = "/trigger", method = RequestMethod.GET)
    public ResponseEntity<String> triggerEmail() {

        String content = this.emailService.notifyStoreAboutOrder(null, null);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_HTML);
        System.out.println(content);
       //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<String>(content, responseHeaders, HttpStatus.NOT_FOUND);

    }

}