package mk.ukim.finki.soa.workoutprogramsservice.config.filters;

import io.jsonwebtoken.Claims;
import mk.ukim.finki.soa.workoutprogramsservice.config.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(httpServletRequest);

            if(token == null) {
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }

            if(!jwtProvider.validateToken(token)) {
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }

            String email = jwtProvider.getEmailFromToken(token);
            String scopes = jwtProvider.getClaims(token).get("roles").toString();
            String scopesAsList = scopes.substring(scopes.indexOf("[") + 1, scopes.indexOf("]"));

            if(!scopesAsList.isEmpty()) {
                List<String> roles = Arrays.asList(scopesAsList.split(","));
                if(!roles.contains("WORKOUT_PROGRAMS")) {
                    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                    return;
                }

                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                roles.forEach(role -> {
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
                    authorities.add(authority);
                });
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(email, null, authorities));
            }

        } catch (Exception e) {
            logger.error("Failure in the 'doFilterInternal' method of the JwtTokenFilter class!");
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getToken(HttpServletRequest httpServletRequest) {
        String authRequest = httpServletRequest.getHeader("Authorization");

        if (authRequest != null && authRequest.startsWith("Bearer "))
            return authRequest.replace("Bearer ", "");

        return null;
    }

}
