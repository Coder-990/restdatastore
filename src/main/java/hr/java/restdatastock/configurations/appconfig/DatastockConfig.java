package hr.java.restdatastock.configurations.appconfig;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.AbstractPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DatastockConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }



    //    @Bean
//    public ObjectMapper objectMapper(){
//        return new ObjectMapper();
//    }
}
