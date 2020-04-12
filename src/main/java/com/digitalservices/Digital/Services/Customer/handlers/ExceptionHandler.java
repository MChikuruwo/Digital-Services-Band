package com.digitalservices.Digital.Services.Customer.handlers;

import com.digitalservices.Digital.Services.Customer.exceptions.OTPException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ModelAndView mapper(OTPException otp) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("OTP");
        mav.addObject("error", otp.getLocalizedMessage());
        return mav;
    }
}