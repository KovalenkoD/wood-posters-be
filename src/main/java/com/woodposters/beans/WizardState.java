package com.woodposters.beans;

import com.woodposters.entity.quote.SalesOrder;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WizardState {
    private Locale locale = Locale.Russian;

    private SalesOrder salesOrder = new SalesOrder();

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }
}
