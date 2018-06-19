package com.woodposters.service.email;

import com.woodposters.entity.quote.Contact;
import com.woodposters.entity.quote.SalesOrder;
import com.woodposters.service.mailContent.MailContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

  /*  @Autowired
    MailSender mailSender;*/

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    MailSender mailSender2;

    @Autowired
    private MailContentBuilder mailContentBuilder;



    @Override
    public String notifyStoreAboutOrder(SalesOrder salesOrder, Contact contact) {

        String mailConetnt = mailContentBuilder.build("TESSTT");

        /*MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("woodposters.store@gmail.com");
            messageHelper.setTo("woodposters.order@gmail.com");
            messageHelper.setSubject("Sample mail subject");
            messageHelper.setText(mailConetnt, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            e.printStackTrace();
        }*/
        return mailConetnt;
    }

    @Override
    public void notifyCustomerAboutOrder(SalesOrder salesOrder, Contact contact) {

    }
}
