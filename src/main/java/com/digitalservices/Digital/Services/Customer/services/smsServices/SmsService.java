package com.digitalservices.Digital.Services.Customer.services.smsServices;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.scheduling.annotation.Async;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SmsService {

    private final SmsSender smsSender;

    @Autowired
    public SmsService(@Qualifier("twilio") SmsGatewaySender smsSender) {
        this.smsSender = smsSender;
    }

    @Async
    public void sendSms(SmsRequest smsRequest) {
        smsSender.sendSms(smsRequest);
    }
}
