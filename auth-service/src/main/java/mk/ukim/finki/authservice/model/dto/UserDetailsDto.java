package mk.ukim.finki.authservice.model.dto;

import lombok.Data;
import mk.ukim.finki.authservice.model.User;
import mk.ukim.finki.authservice.model.enumeration.Role;

import java.util.Set;

@Data
public class UserDetailsDto {
    private String username;
    private Set<Role> roles;

    public static UserDetailsDto of(User user) {
        UserDetailsDto details = new UserDetailsDto();
        details.username = user.getUsername();
        details.roles = user.getRoles();
        return details;
    }
}

