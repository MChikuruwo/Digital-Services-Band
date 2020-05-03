package zw.digitalservices.Digital.Services.Customer.services.smsServices;

import zw.digitalservices.Digital.Services.Customer.models.OptIn;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class RegistrationSmsRequest {

    private final OptIn optIn;

    @NotBlank
    private  String mobileNumber; // destination

    @NotBlank
    private  String message;

    public RegistrationSmsRequest(@JsonProperty("mobileNumber") String mobileNumber, @JsonProperty("message") String message, OptIn optIn) {
        this.mobileNumber = mobileNumber;
        this.message = message;
        this.optIn = optIn;
    }

    public RegistrationSmsRequest(OptIn optIn) {

        this.optIn = optIn;

    }


    public String getPhoneNumber(String mobileNumber) {
        return optIn.getMobileNumber();
    }

    public String setPhoneNumber(String mobileNumber) {
        return  mobileNumber;
    }


    public String getMessage() {
        return " Dear \n" + optIn.getMobileNumber() + "\nYou have been Successfully Registered on the Digital Services Band Platform ";
    }

    public String setMessage(String s) {
        return " Dear \n" + optIn.getMobileNumber() + "\nYou have been Successfully Registered on the Digital Services Band Platform ";
    }


    @Override
    public String toString() {
        return "SmsRequest{" +
                "phoneNumber= ..." + '\'' +
                ", message='" + message + '\'' +
                '}';
    }


    public String setFrom(String mobileNumber) {
        return mobileNumber;
    }
}

