package hr.java.restdatastock.configurations.appconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfigurer {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
