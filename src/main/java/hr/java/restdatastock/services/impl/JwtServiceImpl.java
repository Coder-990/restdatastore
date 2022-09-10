package hr.java.restdatastock.services.impl;

import hr.java.restdatastock.configurations.appconfig.UserDetailsServiceConfiguration;
import hr.java.restdatastock.configurations.security.util.JwtUtil;
import hr.java.restdatastock.model.entities.JwtRequestEntity;
import hr.java.restdatastock.model.entities.JwtResponseEntity;
import hr.java.restdatastock.services.JwtService;
import hr.java.restdatastock.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final UserService userService;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceConfiguration userDetailsServiceConfiguration;

    @Override
    public JwtResponseEntity createJwtToken(JwtRequestEntity jwtRequestEntity) {
        final String username = jwtRequestEntity.getUsername();
//        final String userPassword = jwtRequestEntity.getUserPassword();
        this.authenticate(username, jwtRequestEntity.getUserPassword());
//        final UserDetails userDetails = this.loadUserByUsername(username);
//        String generatedToken = this.jwtUtil.generateToken(this.loadUserByUsername(username));
//        UserEntity userByUsername = this.userService.getByUsername(username);
        return JwtResponseEntity.builder()
                .user(this.userService.getByUsername(username))
                .jwtToken(this.jwtUtil.generateToken(this.userDetailsServiceConfiguration.userDetailsService()
                        .loadUserByUsername(username)))
                .build();
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        final UserEntity userByUsername = this.userService.getByUsername(username);
//        if (userByUsername != null) {
//            return this.buildUserDetails(userByUsername);
//        }
//        throw new UsernameNotFoundException("Username is invalid");
//    }


    private void authenticate(final String username, final String userPassword) throws RuntimeException {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, userPassword));
        } catch (DisabledException ex) {
            log.error("User is disabled ", ex);
            throw new RuntimeException("Bad credentials from user ", ex);
        } catch (BadCredentialsException ex) {
            log.error("Bad credentials from user", ex);
            throw new RuntimeException("Bad credentials from user", ex);
        }
    }
}
