package zw.digitalservices.Digital.Services.Customer.services.smsServices;

import org.springframework.context.annotation.PropertySource;

@PropertySource("application.properties")
public interface Constants {

    String USERNAME = "sms.username";
    String PASSWORD = "sms.password";
    String SENDER_ID = "sms.sender-id";
}