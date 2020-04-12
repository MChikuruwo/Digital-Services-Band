package com.digitalservices.Digital.Services.Customer.services.smsServices;

import com.digitalservices.Digital.Services.Customer.config.SmsConfig;
import com.twilio.Twilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsInitializer {

    private final static Logger LOGGER = LoggerFactory.getLogger(SmsInitializer.class);

    private final SmsConfig smsConfig;

    @Autowired
    public SmsInitializer(SmsConfig smsConfig) {
        this.smsConfig = smsConfig;
        Twilio.init(
                smsConfig.getAccountSid(),
                smsConfig.getAuthToken()
        );
        LOGGER.info("Twilio initialized ... with account sid {} ", smsConfig.getAccountSid());    }
}


