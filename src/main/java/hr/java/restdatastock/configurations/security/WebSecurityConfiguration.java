package hr.java.restdatastock.configurations.security;

import hr.java.restdatastock.configurations.appconfig.JwtAuthenticationEntryPoint;
import hr.java.restdatastock.configurations.security.filter.CustomAuthenticationFilter;
import hr.java.restdatastock.configurations.security.filter.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

//    private final JwtRequestFilter jwtRequestFilter;

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity httpSecurity, final AuthenticationManager authenticationManager) throws Exception {
        httpSecurity.cors();
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/authenticate").permitAll()
//                                .antMatchers(HttpMethod.POST, "/login/**").permitAll()
//                                .antMatchers(HttpMethod.POST, "/users/**").hasAuthority("ADMIN")
                .antMatchers(HttpHeaders.ALLOW).permitAll()
                .anyRequest().authenticated().and().exceptionHandling()
                .authenticationEntryPoint(this.jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilter(new CustomAuthenticationFilter(authenticationManager))
                .addFilterBefore(new JwtRequestFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class)
                .headers().cacheControl();
        return httpSecurity.build();
    }


    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder);
    }

}
