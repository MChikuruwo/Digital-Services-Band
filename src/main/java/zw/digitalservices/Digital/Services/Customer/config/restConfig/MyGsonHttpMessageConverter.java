package zw.digitalservices.Digital.Services.Customer.config.restConfig;



import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*@Component
public class MyGsonHttpMessageConverter extends GsonHttpMessageConverter {
    public MyGsonHttpMessageConverter() {
        List<MediaType> types = Arrays.asList(
                new MediaType("text", "html", DEFAULT_CHARSET),
                new MediaType("application", "json", DEFAULT_CHARSET),
                new MediaType("application", "*+json", DEFAULT_CHARSET),
                new MediaType("text","xml",DEFAULT_CHARSET)
        );
        super.setSupportedMediaTypes(types);
    }

}

 */

