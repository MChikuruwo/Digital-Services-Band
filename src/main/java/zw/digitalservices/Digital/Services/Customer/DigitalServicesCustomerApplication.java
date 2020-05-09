package zw.digitalservices.Digital.Services.Customer;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan("zw.digitalservices.Digital.Services.Customer.services")
@ComponentScan("zw.digitalservices.Digital.Services.Customer.config")
public class DigitalServicesCustomerApplication {

	public static void main(String[] args)  {
		SpringApplication.run(DigitalServicesCustomerApplication.class, args);



	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		/*RestTemplate restTemplate = new RestTemplate();

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		Jaxb2RootElementHttpMessageConverter jaxbMessageConverter = new Jaxb2RootElementHttpMessageConverter();
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.APPLICATION_XML);
		mediaTypes.add(MediaType.APPLICATION_JSON);
		jaxbMessageConverter.setSupportedMediaTypes(mediaTypes);
		messageConverters.add(jaxbMessageConverter);
		jaxbMessageConverter.setSupportDtd(true);
		restTemplate.setMessageConverters(messageConverters);


		 */

		return restTemplateBuilder.messageConverters().build();
	}


}










