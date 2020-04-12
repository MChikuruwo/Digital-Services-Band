package com.digitalservices.Digital.Services.Customer.services.smsServices;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;


public class SmsRequest {

    @NotBlank
    private  String phoneNumber; // destination

    @NotBlank
    private  String message;

    public SmsRequest(@JsonProperty("phoneNumber") String phoneNumber,
                      @JsonProperty("message") String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public SmsRequest() {

    }

    public String getPhoneNumber(String mobileNumber) {
        return phoneNumber;
    }

    public String setPhoneNumber(String mobileNumber) {
        return phoneNumber;
    }


    public String getMessage() {
        return message;
    }

    public String setMessage(String s) {
        return message;
    }


    @Override
    public String toString() {
        return "SmsRequest{" +
                "phoneNumber= ..." + '\'' +
                ", message='" + message + '\'' +
                '}';
    }


    public String setFrom(String mobileNumber) {
        return phoneNumber;
    }
}