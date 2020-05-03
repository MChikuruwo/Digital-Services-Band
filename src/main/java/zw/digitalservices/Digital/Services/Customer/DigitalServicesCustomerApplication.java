package zw.digitalservices.Digital.Services.Customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import zw.digitalservices.Digital.Services.Customer.config.restConfig.JaxbConfig;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@ComponentScan("zw.digitalservices.Digital.Services.Customer.services")
@ComponentScan("zw.digitalservices.Digital.Services.Customer.config")
public class DigitalServicesCustomerApplication {

	public static void main(String[] args) throws JAXBException {
	SpringApplication.run(DigitalServicesCustomerApplication.class, args);

		JaxbConfig jaxbConfig = new JaxbConfig();
		jaxbConfig.marshall();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

	@Bean
	public RestTemplate restTemplate() {
		final RestTemplate restTemplate = new RestTemplate();

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);

		return restTemplate;
	}

	@Bean
	public GsonHttpMessageConverter gsonHttpMessageConverter(Gson gson) {
		GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
		converter.setGson(gson);
		List<MediaType> supportedMediaTypes = converter.getSupportedMediaTypes();
		if (! supportedMediaTypes.contains(MediaType.ALL)) {
			supportedMediaTypes = new ArrayList<>(supportedMediaTypes);
			supportedMediaTypes.add(MediaType.ALL);
			converter.setSupportedMediaTypes(supportedMediaTypes);
		}
		return converter;
	}




	}




