package mk.ukim.finki.authservice.config;

import mk.ukim.finki.authservice.model.User;

public class PrincipalUserFactory {
    public static PrincipalUser build(User user) {
        return new PrincipalUser(user.getUsername(), user.getPassword(), null);
    }
}
