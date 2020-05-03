package zw.digitalservices.Digital.Services.Customer.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

    @Configuration
    @PropertySource("application.properties")
    public class SmsConfig {

        private String userName;
        private String password;
        private String senderId;
        private  String smsId;

        public SmsConfig() {

        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSenderId() {
            return senderId;
        }

        public void setSenderId(String senderId) {
            this.senderId = senderId;
        }

        public String getSmsId() {
            return smsId;
        }

        public void setSmsId(String smsId) {
            this.smsId = smsId;
        }
    }



