package com.digitalservices.Digital.Services.Customer.services.smsServices.util;

import com.digitalservices.Digital.Services.Customer.models.User;
import com.digitalservices.Digital.Services.Customer.services.smsServices.Constants;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
@PropertySource(value = "classpath:application.properties")
public class NotificationUtils {
    @Autowired
    private Environment env;

    public String sendSMS(User user) {
        Twilio.init(env.getProperty(Constants.ACCOUNT_SID),
                env.getProperty(Constants.AUTH_TOKEN));

        String otp=generateOTP();
        MessageCreator message = Message.creator(
                new PhoneNumber(user.getMobileNumber()),
                new PhoneNumber(env.getProperty(Constants.TRIAL_NO)), "Dear User: "
                        + user.getMobileNumber()
                        + " your OTP to activate account : " +otp );
        message.create();
        return otp;
    }

    private String generateOTP() {
        return String.valueOf(new Random().nextInt(95509));
    }
}

