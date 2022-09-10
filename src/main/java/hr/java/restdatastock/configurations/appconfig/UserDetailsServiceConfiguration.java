package hr.java.restdatastock.configurations.appconfig;

import hr.java.restdatastock.model.entities.UserEntity;
import hr.java.restdatastock.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class UserDetailsServiceConfiguration {

    private final UserService userService;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            final UserEntity userByUsername = userService.getByUsername(username);
            if (userByUsername != null) {
                return buildUserDetails(userByUsername);
            }
            throw new UsernameNotFoundException("Username is invalid");
        };
    }

    private UserDetails buildUserDetails(final UserEntity userEntity) {
        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(this.getAuthorities(userEntity))
                .build();
    }

    private Set<SimpleGrantedAuthority> getAuthorities(final UserEntity userEntity) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        userEntity.getRoles().forEach(roleEntity -> authorities.add(
                new SimpleGrantedAuthority("ROLE_" + roleEntity.getName())));
        return authorities;
    }

}
