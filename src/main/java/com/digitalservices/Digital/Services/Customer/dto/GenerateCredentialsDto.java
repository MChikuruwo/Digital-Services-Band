package com.digitalservices.Digital.Services.Customer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateCredentialsDto {
    private String mobileNumber;

    public GenerateCredentialsDto() {
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
