package zw.digitalservices.Digital.Services.Customer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDto {
    private Integer id;
    private String mobileNumber;
    private String otp;
    private Boolean isActive;
}
