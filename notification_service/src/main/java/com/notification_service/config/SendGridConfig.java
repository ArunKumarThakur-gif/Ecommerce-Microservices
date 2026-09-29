package com.notification_service.config;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendGridConfig {

    @Value("${send-grid-api-key}")
    private String apiKey;

    @Bean
    public SendGrid getSendGrid() {
        return new SendGrid(apiKey);
    }
}
