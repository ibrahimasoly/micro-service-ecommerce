package com.ibe.notification.email;

import lombok.Getter;

public enum EmailTemplats {
    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment successfully processed"),
    ORDER_CONFIRMATION("order-confirmation.html", "order confirmation");

    @Getter
    private final String template;
    @Getter
    private final String subject;
    private EmailTemplats(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }

    
}
