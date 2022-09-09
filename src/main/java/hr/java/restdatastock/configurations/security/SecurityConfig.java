//package hr.java.restdatastock.configs.security;
//
//import hr.java.restdatastock.configs.security.filter.CustomAuthenticationFilter;
//import hr.java.restdatastock.configs.security.filter.CustomAuthorizationFilter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//
//public class SecurityConfig {
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
//        http.csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeHttpRequests((authz) -> authz
//                        .antMatchers(HttpMethod.POST, "/login/**").permitAll()
//                        .antMatchers(HttpMethod.POST, "/users/**").hasAuthority("ROLE_ADMIN")
//                        .anyRequest().authenticated())
//                .addFilter(new CustomAuthenticationFilter(authenticationManager))
//                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .headers()
//                .cacheControl();
//        return http.build();
//    }
//
//}
//