package hr.java.restdatastock.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.AbstractPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DatastockConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new AbstractPasswordEncoder() {
            @Override
            protected byte[] encode(CharSequence charSequence, byte[] bytes) {
                return new byte[0];
            }
        };
    }
//    @Bean
//    public ObjectMapper objectMapper(){
//        return new ObjectMapper();
//    }
}
