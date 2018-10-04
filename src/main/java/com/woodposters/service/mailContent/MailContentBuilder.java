package com.woodposters.service.mailContent;

import com.woodposters.entity.quote.Contact;
import com.woodposters.entity.quote.SalesOrder;

public interface MailContentBuilder {
     String build(SalesOrder salesOrder);

     String buildForPosters(SalesOrder salesOrder, Contact contact);
}
