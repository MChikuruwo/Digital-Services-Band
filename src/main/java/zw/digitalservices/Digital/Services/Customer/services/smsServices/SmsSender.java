package zw.digitalservices.Digital.Services.Customer.services.smsServices;

public interface SmsSender {
    void sendSms(SmsRequest smsRequest);
    void sendRegistrationSms(RegistrationSmsRequest registrationSmsRequest);
}
