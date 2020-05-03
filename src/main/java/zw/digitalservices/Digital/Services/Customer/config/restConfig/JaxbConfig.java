package zw.digitalservices.Digital.Services.Customer.config.restConfig;

import zw.digitalservices.Digital.Services.Customer.services.smsServices.SmsRequests;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.File;

@Configuration
public class JaxbConfig {

    public void marshall() throws JAXBException {
        try {
            //SmsRequest smsRequest = new SmsRequest();
            SmsRequests smsRequests = new SmsRequests();
            JAXBContext jc = JAXBContext.newInstance( SmsRequests.class/*, SmsRequest.class*/);
            Marshaller ms = jc.createMarshaller();
            ms.getProperty(Marshaller.JAXB_FORMATTED_OUTPUT);
            ms.marshal(smsRequests, System.out);
           // ms.marshal(smsRequest, System.out);
             File file = new File("smslist.xml");
            ms.marshal(smsRequests,file);
            //File file1 = new File("sms.xml");
            //ms.marshal(smsRequest,file);

        } catch (PropertyException e) {
            System.out.println(""+e.getMessage());


        }

    }

}




