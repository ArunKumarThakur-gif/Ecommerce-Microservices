package com.notification_service.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class SendGridEmailService {

    private final SendGrid sendGrid;

    @Value("${to-email}")
    private String to;

    public SendGridEmailService(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    public void sendEmail(String to, String subject, String body) throws IOException {
        Email from = new Email(to);
        Email recipient = new Email(to);
        Content content = new Content("text/html", body);
        Mail mail = new Mail(from, subject, recipient, content);

        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("");
        request.setBody(mail.build());

        sendGrid.api(request);
    }
}
