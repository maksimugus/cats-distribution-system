package itmo.tech.lab5api.security.user;

import itmo.tech.lab5domain.user.User;
import itmo.tech.lab5domain.user.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto saveUser(User user);

    UserDto getCurrentUser();
}
