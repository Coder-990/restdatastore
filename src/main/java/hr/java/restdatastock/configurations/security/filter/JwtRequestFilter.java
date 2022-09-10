package hr.java.restdatastock.configurations.security.filter;

import hr.java.restdatastock.configurations.security.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@Configuration
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

//    private final JwtUtil jwtUtil;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = null;
        String username = null;

        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7);
            try {
                username = new JwtUtil().getUsernameFromToken(jwtToken);
//                username = jwtUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException ex) {
                log.error("Unable to get JWT token", ex);
            } catch (ExpiredJwtException ex) {
                log.error("Jwt token is expired ", ex);
            }
        } else {
            log.info("Jwt token does not start with Bearer");
        }
        this.isUsernameNotNullAndAuthenticationNull(request, jwtToken, username);
        filterChain.doFilter(request, response);
    }
//        String token = null;
//        if(request.getServletPath().equals("/login") || request.getServletPath().equals("/refreshToken")) {
//            filterChain.doFilter(request, response);
//        } else {
//            String authorizationHeader = request.getHeader(AUTHORIZATION);
//            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//                try {
//                    token = authorizationHeader.substring("Bearer ".length());
//                    UsernamePasswordAuthenticationToken authenticationToken = JwtUtil.parseToken(token);
//                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                    filterChain.doFilter(request, response);
//                }
//                catch (Exception e) {
//                    log.error(String.format("Error auth token: %s", token), e);
//                    response.setStatus(FORBIDDEN.value());
//                    Map<String, String> error = new HashMap<>();
//                    error.put("errorMessage", e.getMessage());
//                    response.setContentType(APPLICATION_JSON_VALUE);
//                    new ObjectMapper().writeValue(response.getOutputStream(), error);
//                }
//            } else {
//                filterChain.doFilter(request, response);
//            }
//        }

    private void isUsernameNotNullAndAuthenticationNull(final HttpServletRequest request, final String jwtToken, final String username) {
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            boolean isTokenValidated = new JwtUtil().validateToken(jwtToken, userDetails);
            if (isTokenValidated) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
    }
}

