package mk.ukim.finki.authservice.service.impl;

import mk.ukim.finki.authservice.config.jwt.JwtProvider;
import mk.ukim.finki.authservice.model.User;
import mk.ukim.finki.authservice.model.dto.LoginDto;
import mk.ukim.finki.authservice.model.dto.ResponseDto;
import mk.ukim.finki.authservice.model.enumeration.Role;
import mk.ukim.finki.authservice.service.AuthService;
import mk.ukim.finki.authservice.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public AuthServiceImpl(UserService userService, PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public ResponseDto register(User user) {
        String email = user.getUsername();
        ResponseDto response = new ResponseDto();

        if (this.userService.existsByUsername(email)) {
            response.setStatusCode(401);
            response.setMessage(String.format("The user with the provided email (%s) already exists!", email));
            response.setUser(null);
        } else {
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            Set<Role> roles = new HashSet<>();
            roles.addAll(Arrays.asList(Role.values()));

            user.setRoles(roles);
            user = this.userService.register(user);

            response.setStatusCode(200);
            response.setMessage("Successful registration!");
            response.setUser(user);
        }

        return response;
    }

    @Override
    public ResponseDto login(LoginDto loginDto) {
        String email = loginDto.getUsername();
        ResponseDto response = new ResponseDto();

        if (!this.userService.existsByUsername(email)) {
            response.setStatusCode(401);
            response.setMessage(String.format("The user with the provided email (%s) does not exist!", email));
            response.setUser(null);
        } else {
            User user = this.userService.findByUsername(email);
            String password = loginDto.getPassword();

            if (!this.passwordEncoder.matches(password, user.getPassword())) {
                response.setStatusCode(401);
                response.setMessage("Passwords do not match!");
                response.setUser(null);
            } else {
                Authentication authentication = this.authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(email, password));
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
                String jwt = this.jwtProvider.generateToken(authentication);

                response.setStatusCode(200);
                response.setMessage("Successful registration!");
                response.setUser(user);
                response.setToken(jwt);
            }
        }

        return response;
    }

    @Override
    public ResponseDto logout(HttpServletRequest request) throws ServletException {
        ResponseDto response = new ResponseDto();

        request.logout();

        response.setStatusCode(200);
        response.setMessage("Successful logging out!");
        response.setUser(null);

        return response;
    }

    @Override
    public ResponseDto getCurrentUser(Authentication authentication) {
        ResponseDto response = new ResponseDto();

        if (authentication == null) {
            response.setStatusCode(404);
            response.setMessage("There are no currently signed in users!");
            response.setUser(null);
        } else {
            String userEmail = (String) authentication.getPrincipal();
            User user = this.userService.findByUsername(userEmail);

            response.setStatusCode(200);
            response.setMessage("User found!");
            response.setUser(user);
        }

        return response;
    }
}
