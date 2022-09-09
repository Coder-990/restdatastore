package hr.java.restdatastock.services.impl;

import hr.java.restdatastock.configurations.security.util.JwtUtil;
import hr.java.restdatastock.model.entities.JwtRequestEntity;
import hr.java.restdatastock.model.entities.JwtResponseEntity;
import hr.java.restdatastock.model.entities.UserEntity;
import hr.java.restdatastock.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements UserDetailsService {


    private final UserService userService;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    public JwtResponseEntity createJwtToken(JwtRequestEntity jwtRequestEntity) {
        final String username = jwtRequestEntity.getUsername();
//        final String userPassword = jwtRequestEntity.getUserPassword();
        this.authenticate(username, jwtRequestEntity.getUserPassword());
//        final UserDetails userDetails = this.loadUserByUsername(username);
//        String generatedToken = this.jwtUtil.generateToken(this.loadUserByUsername(username));
//        UserEntity userByUsername = this.userService.getByUsername(username);
        return JwtResponseEntity.builder()
                .user(this.userService.getByUsername(username))
                .jwtToken(this.jwtUtil.generateToken(this.loadUserByUsername(username)))
                .build();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity userByUsername = this.userService.getByUsername(username);
        if (userByUsername != null) {
            return this.buildUserDetails(userByUsername);
        }
        throw new UsernameNotFoundException("Username is invalid");
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
