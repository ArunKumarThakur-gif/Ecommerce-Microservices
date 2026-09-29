package com.notification_service.services;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserRegistrationListener {

    private final SendGridEmailService emailService;

    public UserRegistrationListener(SendGridEmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "user-events", groupId = "notification-service")
    public void handleUserRegistration(String message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(message);

        String to = node.get("email").asText();
        String name = node.get("name").asText();

        String subject = "Welcome to Our Store!";
        String body = "<h1>Hi " + name + ",</h1>"
                + "<p>Thanks for registering with us. "
                + "We’re excited to have you onboard!</p>";

        emailService.sendEmail(to, subject, body);
    }
}

