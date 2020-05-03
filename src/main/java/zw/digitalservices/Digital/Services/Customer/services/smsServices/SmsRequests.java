package zw.digitalservices.Digital.Services.Customer.services.smsServices;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Collection;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="smslist")
public class SmsRequests implements  Serializable{

    @XmlElement(name = "sms")
    private SmsRequest smsRequests;

    public SmsRequest getSmsRequests() {
        return smsRequests;
    }

    public void setSmsRequests(SmsRequest smsRequests) {
        this.smsRequests = smsRequests;
    }

    @Override
    public String toString() {
        return "SmsRequests{" +
                "smsRequests=" + smsRequests +
                '}';
    }
}
