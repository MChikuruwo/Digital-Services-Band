package com.digitalservices.Digital.Services.Customer.services.smsServices;
/*import com.digitalservices.Digital.Services.Customer.config.SmsConfig;
import com.digitalservices.Digital.Services.Customer.models.User;
import com.twilio.Twilio;
import org.slf4j.Logger;
import org.springframework.core.env.Environment;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource("classpath:application.properties")
public class SmsInitializer {

    private final static Logger LOGGER = LoggerFactory.getLogger(SmsInitializer.class);

    private final SmsConfig smsConfig;


    @Autowired
    private Environment env;

    public String sendSMS(User user) {
        Twilio.init(env.getProperty(Constants.ACCOUNT_SID),
                env.getProperty(Constants.AUTH_TOKEN));



   /* @Autowired
    public SmsInitializer(SmsConfig smsConfig) {
            this.smsConfig = smsConfig;
            Twilio.init(
                    smsConfig.getAccountSid(),
                    smsConfig.getAuthToken()
            );
            LOGGER.info("Twilio initialized ... with account sid {} ", smsConfig.getAccountSid());
        }
    }

    */






