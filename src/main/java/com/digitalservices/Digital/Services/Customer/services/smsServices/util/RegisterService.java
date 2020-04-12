package com.digitalservices.Digital.Services.Customer.services.smsServices.util;

import com.digitalservices.Digital.Services.Customer.exceptions.OTPException;
import com.digitalservices.Digital.Services.Customer.models.User;
import com.twilio.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterService {
    @Autowired
    private NotificationUtils utils;

    public static List<String> otp;
    public static List<User> activeUser;
    static {
        otp = new ArrayList<>();
        activeUser = new ArrayList<>();
    }

    public String Register(User user) throws OTPException {
        try {
            otp.add(utils.sendSMS(user));
            activeUser.add(user);
        } catch (ApiException e) {
            throw new OTPException(
                    "Internal Server issue Possible error  Number may not contains country code or  May not Registered in Twillo ,click on Back button to Re-Registration");
        }
        return "Thanks for giving your details";
    }

    public User getActiveUser() {
        return activeUser.get(0);
    }
}