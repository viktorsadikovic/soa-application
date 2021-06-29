package mk.ukim.finki.authservice.service;

import mk.ukim.finki.authservice.model.User;
import mk.ukim.finki.authservice.model.dto.LoginDto;
import mk.ukim.finki.authservice.model.dto.ResponseDto;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    ResponseDto register(User user);

    ResponseDto login(LoginDto loginDto);

    ResponseDto logout(HttpServletRequest request) throws ServletException;

    ResponseDto getCurrentUser(Authentication authentication);
}
