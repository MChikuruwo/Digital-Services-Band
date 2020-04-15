package com.digitalservices.Digital.Services.Customer.services.smsServices;
import com.digitalservices.Digital.Services.Customer.config.SmsConfig;
import com.digitalservices.Digital.Services.Customer.models.User;
import com.twilio.Twilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsInitializer {

    private final static Logger LOGGER = LoggerFactory.getLogger(SmsInitializer.class);

    private final SmsConfig twilioConfiguration;

    @Autowired
    public SmsInitializer(SmsConfig twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
        Twilio.init(
                twilioConfiguration.getAccountSid(),
                twilioConfiguration.getAuthToken()
        );
        LOGGER.info("Twilio initialized ... with account sid {} ", twilioConfiguration.getAccountSid());
    }
}