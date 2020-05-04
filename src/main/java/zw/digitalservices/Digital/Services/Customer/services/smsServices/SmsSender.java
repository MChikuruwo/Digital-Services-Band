package zw.digitalservices.Digital.Services.Customer.services.smsServices;

public interface SmsSender {
    void sendSms(Sms sms);
    void sendRegistrationSms(RegistrationSmsRequest registrationSmsRequest);
}
