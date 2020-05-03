package zw.digitalservices.Digital.Services.Customer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.XmlBeamHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@EnableWebMvc
@Component
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer
                .setUseSuffixPatternMatch(false);  // to use special character in path variables, for example, `email@dom.com`
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .favorPathExtension(false); // to  avoid HttpMediaTypeNotAcceptableException on standalone tomcat
    }

   @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        // http
        HttpMessageConverter converter = new StringHttpMessageConverter();
        converters.add(converter);

        // string
        converter = new FormHttpMessageConverter();
        converters.add(converter);

        // json
        converter = new MappingJackson2HttpMessageConverter();
        converters.add(converter);

        //XML
        converter = new Jaxb2RootElementHttpMessageConverter();

         converters.add(converter);

    }

    
   /* @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, new MappingJackson2XmlHttpMessageConverter(
                new Jackson2ObjectMapperBuilder()
                        .defaultUseWrapper(false)
                        .createXmlMapper(true)
                        .build()
        ));
    }

    */
}

