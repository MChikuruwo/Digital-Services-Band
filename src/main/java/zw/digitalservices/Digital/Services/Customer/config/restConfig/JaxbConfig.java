package zw.digitalservices.Digital.Services.Customer.config.restConfig;

import zw.digitalservices.Digital.Services.Customer.services.smsServices.SmsList;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

@Configuration
public class JaxbConfig {

    public void marshall() throws JAXBException {
            SmsList smsList = new SmsList();
            JAXBContext jaxbContext = JAXBContext.newInstance(SmsList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //Marshal the smslist list in console
            jaxbMarshaller.marshal(smsList, System.out);

            //Marshal the smslist list in file
             File file = new File("smslist.xml");
            jaxbMarshaller.marshal(smsList,file);


    }


}




