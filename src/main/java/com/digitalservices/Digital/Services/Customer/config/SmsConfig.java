package com.digitalservices.Digital.Services.Customer.config;

import com.digitalservices.Digital.Services.Customer.services.smsServices.Constants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

    @Configuration
    @ConfigurationProperties("twilio")
    public class SmsConfig {

        private String accountSid;
        private String authToken;
        private String trialNumber;

        public SmsConfig() {

        }

        public String getAccountSid() {
            return accountSid;
        }

        public void setAccountSid(String accountSid) {
            this.accountSid = accountSid;
        }

        public String getAuthToken() {
            return authToken;
        }

        public void setAuthToken(String authToken) {
            this.authToken = authToken;
        }

        public String getTrialNumber() {
            return trialNumber;
        }

        public void setTrialNumber(String trialNumber) {
            this.trialNumber = trialNumber;
        }
    }

