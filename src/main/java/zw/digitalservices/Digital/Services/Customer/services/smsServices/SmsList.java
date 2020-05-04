package zw.digitalservices.Digital.Services.Customer.services.smsServices;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name="smslist")
@XmlAccessorType(XmlAccessType.FIELD)
public class SmsList implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "sms")
    private Sms sms;

    public SmsList() {
        super();
    }

    public SmsList(Sms sms) {
        super();
        this.sms = sms;
    }


    public Sms getSms() {
        return sms;
    }

    public void setSms(Sms sms) {
        this.sms = sms;
    }

    @Override
    public String toString() {
        return "SmsList{" +
                "sms=" + sms +
                '}';
    }
}
