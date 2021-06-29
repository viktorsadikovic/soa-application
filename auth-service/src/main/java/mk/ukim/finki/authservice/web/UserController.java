package mk.ukim.finki.authservice.web;

import lombok.AllArgsConstructor;
import mk.ukim.finki.authservice.model.User;
import mk.ukim.finki.authservice.model.dto.LoginDto;
import mk.ukim.finki.authservice.model.dto.ResponseDto;
import mk.ukim.finki.authservice.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class UserController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseDto login(@RequestBody LoginDto loginDto) {
        return this.authService.login(loginDto);
    }

    @PostMapping("/register")
    public ResponseDto register(@RequestBody User user) {
        return this.authService.register(user);
    }

    @PostMapping("/logout")
    public ResponseDto logout(HttpServletRequest request) throws ServletException {
        return this.authService.logout(request);
    }

    @GetMapping("/current-user")
    public ResponseDto getCurrentUser(Authentication authentication) {
        return this.authService.getCurrentUser(authentication);
    }
}
