package com.digitalservices.Digital.Services.Customer.services.smsServices;

//import com.digitalservices.Digital.Services.Customer.config.SmsConfig;
import com.digitalservices.Digital.Services.Customer.config.SmsConfig;
import com.digitalservices.Digital.Services.Customer.models.User;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

    @Service("twilio")
    public class SmsGatewaySender implements SmsSender {

        private static final Logger LOGGER = LoggerFactory.getLogger(SmsGatewaySender.class);

       private final SmsConfig smsConfig;

        @Autowired
        public SmsGatewaySender(SmsConfig smsConfig) {
            this.smsConfig = smsConfig;
        }

        @Override


        public void sendSms(SmsRequest smsRequest) {
            User user = new User();
            if (isPhoneNumberValid(smsRequest.getPhoneNumber(user.getMobileNumber()))) {
                PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber(user.getMobileNumber()));
                PhoneNumber from = new PhoneNumber(smsConfig.getTrialNumber());
                String message = smsRequest.getMessage();
                MessageCreator creator = Message.creator(to, from, message);
                creator.create();
                LOGGER.info("Send sms {}", smsRequest);
            } else {
                throw new IllegalArgumentException(
                        "Phone number [" + smsRequest.getPhoneNumber(user.getMobileNumber()) + "] is not a valid number"
                );
            }

        }

        private boolean isPhoneNumberValid(String phoneNumber) {
            // TODO: Implement phone number validator
            return true;
        }
}
