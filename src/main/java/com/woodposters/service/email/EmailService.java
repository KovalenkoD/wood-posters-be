package com.woodposters.service.email;

import com.woodposters.entity.quote.Contact;
import com.woodposters.entity.quote.SalesOrder;

public interface EmailService {
    String notifyStoreAboutOrder(SalesOrder salesOrder, Contact contact);

    void notifyCustomerAboutOrder(SalesOrder salesOrder, Contact contact);
}
