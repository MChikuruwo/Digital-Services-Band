package zw.digitalservices.Digital.Services.Customer.services.smsServices;

/*import zw.digitalservices.Digital.Services.Customer.config.SmsConfig;
import zw.digitalservices.Digital.Services.Customer.models.OptIn;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.digitalservices.Digital.Services.Customer.models.User;

import java.util.List;

@Service("ewz")
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
            if (isPhoneNumberValid(smsRequest.getMobiles(user.getMobileNumber()))) {
               // PhoneNumber to = new PhoneNumber(smsRequest.getMobiles(user.getMobileNumber())));
                PhoneNumber from = new PhoneNumber(smsConfig.getSenderId());
                String message = smsRequest.getSms();
                //MessageCreator creator = Message.creator(to, from, message);
               // creator.create();
                LOGGER.info("Send sms {}", smsRequest);
            } else {
                throw new IllegalArgumentException(
                        //"Phone number :" + smsRequest.setMobiles(user.getMobileNumber()) + "] is not a valid number"
                );
            }

        }



        @Override
        public void sendRegistrationSms(RegistrationSmsRequest registrationSmsRequest) {
            OptIn optIn = new OptIn();
            if (isPhoneNumberValid(registrationSmsRequest.getPhoneNumber(optIn.getMobileNumber()))) {
                PhoneNumber to = new PhoneNumber(registrationSmsRequest.getPhoneNumber(optIn.getMobileNumber()));
                PhoneNumber from = new PhoneNumber(smsConfig.getSenderId());
                String message = registrationSmsRequest.getMessage();
                MessageCreator creator = Message.creator(to, from, message);
                creator.create();
                LOGGER.info("Send sms {}", registrationSmsRequest);
            } else {
                throw new IllegalArgumentException(
                        "Phone number [" + registrationSmsRequest.getPhoneNumber(optIn.getMobileNumber()) + "] is not a valid number"
                );
            }

        }

        private boolean isPhoneNumberValid(List<SmsRequestBody> phoneNumber) {
            // TODO: Implement phone number validator
            return true;
        }
}

 */
