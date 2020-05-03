package zw.digitalservices.Digital.Services.Customer.dto;

public class AddOptInDto {
    private String mobileNumber;
    private Integer age;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
