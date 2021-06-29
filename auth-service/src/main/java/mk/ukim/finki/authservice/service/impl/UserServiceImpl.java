package mk.ukim.finki.authservice.service.impl;

import mk.ukim.finki.authservice.model.User;
import mk.ukim.finki.authservice.repository.UserRepository;
import mk.ukim.finki.authservice.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsById(username);
    }

    @Override
    public User register(User user) {
        return this.userRepository.save(user);
    }

//    private String generateToken(User user) throws JsonProcessingException {
//        return JWT.create()
//                .withSubject(new ObjectMapper().writeValueAsString(user))
//                .withExpiresAt(new Date(System.currentTimeMillis() + JwtAuthConstants.EXPIRATION_TIME))
//                .sign(Algorithm.HMAC256(JwtAuthConstants.SECRET.getBytes()));
//    }

}
