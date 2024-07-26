package com.ibe.notification.email;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.ibe.notification.dto.ProductResponse;

import static java.nio.charset.StandardCharsets.UTF_8;

import org.springframework.mail.MailException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine engine;

    @Async
    public void sendPaymentSuccesEmail(
        String destinationEmail,
        String customerName,
        BigDecimal amount,
        String orderReference
    ) throws MessagingException{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
        messageHelper.setFrom("bakcouliba@gmail.com");

        final String templateName = EmailTemplats.PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> variable = new HashMap<>();
        variable.put("customerName", customerName);
        variable.put("amount", amount);
        variable.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(variable);
        messageHelper.setSubject(EmailTemplats.PAYMENT_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = engine.process(templateName, context);
            messageHelper.setText(htmlTemplate);
            messageHelper.setTo(destinationEmail);
            javaMailSender.send(mimeMessage);

            log.info(String.format("INFO - Email successfully sent to %s with template %s ", destinationEmail, templateName));
        } catch (MessagingException | MailException e) {
            log.warn("WARNING can't send email to {}", destinationEmail);
        }
    }


    @Async
    public void sendOrderComfirmationEmail(
        String destinatioEmail,
        String cutomerName,
        BigDecimal amount,
        String orderReference,
        List<ProductResponse> products
    ) throws MessagingException{

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, UTF_8.name());

        messageHelper.setFrom("bakcouliba@gmail.com");

        final String templateName = EmailTemplats.ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> variable = new HashMap<>();

        variable.put("amount", amount);
        variable.put("cutomerName", cutomerName);
        variable.put("orderReference", orderReference);
        variable.put("products", products);

        Context context = new Context();
        context.setVariables(variable);
        messageHelper.setSubject(EmailTemplats.ORDER_CONFIRMATION.getSubject());
        try {
            String htmlTemplate = engine.process(templateName, context);
            messageHelper.setText(htmlTemplate);
            messageHelper.setTo(destinatioEmail);
            javaMailSender.send(mimeMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s ", destinatioEmail, templateName));
        } catch (MessagingException | MailException e) {
            log.warn("WARNING can't send email to {}", destinatioEmail);
        }
        
    }
    
}
