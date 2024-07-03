package pl.pjatk.marlew.rentalservice.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.pjatk.marlew.rentalservice.ExceptionHandler.ResponseErrorHandler;

@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new ResponseErrorHandler());
        return restTemplate;
    }
}
