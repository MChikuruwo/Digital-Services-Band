package com.digitalservices.Digital.Services.Customer.services.smsServices.util;


/*import com.digitalservices.Digital.Services.Customer.services.smsServices.Constants;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.Service;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.stereotype.Component;

@Component
public class SmsVerification  {
      private static final String ACCOUNT_SID = Constants.ACCOUNT_SID;
        private static final String AUTH_TOKEN = Constants.AUTH_TOKEN;
        //private static final String VERIFICATION_SID = System.getenv("VERIFICATION_SID");

        public SmsVerification() {
            super();
        }

        Service service = Service.creator("Twilio Verify/OTP Service").create();

        public static String startVerification(String phone, String channel) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Twilio.setUsername(ACCOUNT_SID);
            Twilio.setPassword(AUTH_TOKEN);
            Verification verification = Verification.creator(ACCOUNT_SID, phone, channel).create();
            return (verification.getSid());
        }

        public String checkVerification(String phone, String code) {
            VerificationCheck verification = VerificationCheck.creator(ACCOUNT_SID, code).setTo(phone).create();
            if("approved".equals(verification.getStatus())) {
                return verification.getSid();
            }
            return new String();
        }
}


 */



