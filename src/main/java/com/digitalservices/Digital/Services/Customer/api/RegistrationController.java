package com.digitalservices.Digital.Services.Customer.api;

/*import com.digitalservices.Digital.Services.Customer.exceptions.OTPException;
import com.digitalservices.Digital.Services.Customer.models.User;
import com.digitalservices.Digital.Services.Customer.models.api.ApiResponse;
import com.digitalservices.Digital.Services.Customer.services.UserService;
import com.digitalservices.Digital.Services.Customer.services.smsServices.SmsRequest;
import com.digitalservices.Digital.Services.Customer.services.smsServices.SmsService;
import com.digitalservices.Digital.Services.Customer.services.smsServices.util.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
public class RegistrationController {

    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    private final RegisterService service;
    private final  UserService userService;
    private final SmsService smsService;


    @Autowired
    public RegistrationController(RegisterService service, UserService userService, SmsService smsService){
        this.service = service;
        this.userService  = userService;
        this.smsService = smsService;
    }

    @RequestMapping(value = "/view")
    public String viewRegistration() {
        return "register";
    }

    @PostMapping(value = "/register")
    public ApiResponse register(@ModelAttribute("user") User user, Model model)
            throws OTPException {
        String message = service.Register(user);
        model.addAttribute("message", message);
        model.addAttribute("user", user);
        return new ApiResponse(201,  "SUCCESS", userService.add(user));
    }

    @RequestMapping(value = "/VerifyOTP")
    public String verifyOTP(@RequestParam("otp") String code, Model model) {
        String msg = "";
        String msg1 = "";
        boolean isVerified = RegisterService.otp.stream().anyMatch(
                o -> o.equals(code));
        System.out.println(code);
        System.out.println(RegisterService.otp.get(0));
        if (isVerified) {
            msg = "Verified Successfully";
            model.addAttribute("msg", msg);
            return "facebook";
        } else {
            msg1 = "Invalid OTP Re-Try";
            model.addAttribute("msg1", msg1);
            return "OTP";
        }

    }

    @RequestMapping(value = "/resendOTP")
    public String resendOTP(Model model) throws OTPException {
        User active = service.getActiveUser();
        service.Register(active);
        return "OTP";
    }
}

 */

