package mk.ukim.finki.soa.workoutprogramsservice.config.filters;

import mk.ukim.finki.soa.workoutprogramsservice.config.jwt.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    JwtProvider jwtProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(httpServletRequest);
//            String email = jwtProvider.getEmailFromToken(token);

            if(token == null) {
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }

        } catch (Exception e) {
            logger.error("Failure in the 'doFilterInternal' method of the JwtTokenFilter class!");
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
