package com.digitalservices.Digital.Services.Customer.services.smsServices;

import com.digitalservices.Digital.Services.Customer.models.OptIn;
import com.digitalservices.Digital.Services.Customer.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;


public class SmsRequest {

    private final User user;

    @NotBlank
    private  String mobileNumber; // destination

    @NotBlank
    private  String message;

    public SmsRequest(@JsonProperty("mobileNumber") String mobileNumber, @JsonProperty("message") String message, User user) {
        this.mobileNumber = mobileNumber;
        this.message = message;
        this.user = user;
    }

    public SmsRequest(User user) {

        this.user = user;

    }


    public String getPhoneNumber(String mobileNumber) {
        return user.getMobileNumber();
    }

    public String setPhoneNumber(String mobileNumber) {
        return  mobileNumber;
    }


    public String getMessage() {
        return " Dear " + user.getMobileNumber() + "\nYou have been Signed in on the Digital Services Band Platform your OTP is:\n"  + user.getOtp() +" \nUse it within 24 hours to proceed to the questionnaire";
    }

    public String setMessage(String s) {
        return " Dear " + user.getMobileNumber() + "\nYou have been Signed in on the Digital Services Band Platform your OTP is:\n" + user.getOtp() +" \nUse it within 24 hours to proceed to the questionnaire";
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