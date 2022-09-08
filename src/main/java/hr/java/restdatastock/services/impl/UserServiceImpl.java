package hr.java.restdatastock.services.impl;

//import com.nimbusds.jose.JOSEException;
//import com.nimbusds.jose.proc.BadJOSEException;
//import hr.java.restdatastock.configs.security.JwtUtil;
//import hr.java.restdatastock.model.entities.RoleEntity;

import hr.java.restdatastock.model.entities.RoleEntity;
import hr.java.restdatastock.model.entities.UserEntity;
import hr.java.restdatastock.repositories.RoleRepository;
import hr.java.restdatastock.repositories.UserRepository;
import hr.java.restdatastock.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    static final String USER_NOT_FOUND_MESSAGE = "User with username %s not found";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getAll() {
        log.info("Retrieving all users");
        return this.userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity getByUsername(final String username) {
        log.info("Retrieving user {}", username);
        return this.userRepository.findByUsername(username);
    }

    @Override
    public UserEntity registerNewUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Registering user {} to the database", user.getUsername());
        return this.userRepository.save(user);
    }

    @Override
    public void initRolesAndUsers() {
        RoleEntity roleAdmin = new RoleEntity();
        roleAdmin.setName("ROLE_ADMIN");
        log.info("Registering user {} to the database", roleAdmin.getName());
        this.roleRepository.save(roleAdmin);

        RoleEntity roleUser = new RoleEntity();
        roleUser.setName("ROLE_USER");
        log.info("Registering user {} to the database", roleAdmin.getName());
        this.roleRepository.save(roleUser);

        UserEntity userAdmin = new UserEntity();
        userAdmin.setUsername("Admin");
        userAdmin.setPassword(passwordEncoder.encode("admin"));
        Set<RoleEntity> adminRole = new HashSet<>();
        adminRole.add(roleAdmin);
        userAdmin.setRoles(adminRole);
        this.userRepository.save(userAdmin);

        UserEntity userReader = new UserEntity();
        userReader.setUsername("Reader");
        userReader.setPassword(passwordEncoder.encode("user"));
        Set<RoleEntity> userRoles = new HashSet<>();
        userRoles.add(roleUser);
        userReader.setRoles(userRoles);
        this.userRepository.save(userReader);
    }

    @Override
    public UserEntity addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        UserEntity userEntity = userRepository.findByUsername(username);
//        RoleEntity roleEntity = roleRepository.findByName(roleName);
//        userEntity.getRoles().add(roleEntity);
        return userEntity;
    }

//    @Override
//    @Transactional(readOnly = true)
//    public Map<String, String> refreshToken(String authorizationHeader, String issuer) throws BadJOSEException,
//            JOSEException, ParseException {
//        String refreshToken = authorizationHeader.substring("Bearer ".length());
//        String username = JwtUtil.parseToken(refreshToken).getName();
//        UserEntity userEntity = getByUsername(username);
//        List<String> roles = userEntity.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toList());
//        String accessToken = JwtUtil.createAccessToken(username, issuer, roles);
//        return Map.of("access_token", accessToken, "refresh_token", refreshToken);
//    }

    @Override
    public HttpStatus deleteById(final Long id) {
        final HttpStatus httpStatus;
        if (this.userRepository.deleteFirmeEntityById(id) > 0) {
            httpStatus = HttpStatus.NO_CONTENT;
        } else {
            httpStatus = HttpStatus.UNAUTHORIZED;
        }
        return httpStatus;
    }

//    @Transactional(readOnly = true)
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity user = userRepository.findByUsername(username);
//        if(user == null) {
//            String message = String.format(USER_NOT_FOUND_MESSAGE, username);
//            log.error(message);
//            throw new UsernameNotFoundException(message);
//        } else {
//            log.debug("User found in the database: {}", username);
//            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//            user.getRoles().forEach(role -> {
//                authorities.add(new SimpleGrantedAuthority(role.getName()));
//            });
//            return new User(user.getUsername(), user.getPassword(), authorities);
//        }
}



