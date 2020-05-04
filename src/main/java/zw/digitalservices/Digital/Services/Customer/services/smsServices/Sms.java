package zw.digitalservices.Digital.Services.Customer.services.smsServices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement( name = "sms")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sms implements  Serializable{

    private static final long serialVersionUID = 1L;

     @XmlElement(name = "user")
     private String user;

    @XmlElement(name = "password")
    private String password;

    @XmlElement(name = "mobiles")
    private String mobiles;

    @XmlElement(name = "message")
    private String sms;

    @XmlElement(name = "senderid")
    private String senderId;


     @XmlElement(name = "clientsmsid")
    private String clientSmsId;


    public Sms() {
        super();
    }

    public Sms(String user, String password, String mobiles, String sms, String senderId, String clientSmsId) {
        super();
        this.user = user;
        this.password = password;
        this.mobiles = mobiles;
        this.sms = sms;
        this.senderId = senderId;
        this.clientSmsId = clientSmsId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobiles(String mobileNumber) {
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getClientSmsId() {
        return clientSmsId;
    }
    public void setClientSmsId(String clientSmsId) {
        this.clientSmsId = clientSmsId;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", mobiles='" + mobiles + '\'' +
                ", sms='" + sms + '\'' +
                ", senderId='" + senderId + '\'' +
                ", clientSmsId='" + clientSmsId + '\'' +
                '}';
    }
}