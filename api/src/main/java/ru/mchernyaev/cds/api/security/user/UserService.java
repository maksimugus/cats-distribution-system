package ru.mchernyaev.cds.api.security.user;

import ru.mchernyaev.cds.domain.user.User;
import ru.mchernyaev.cds.domain.user.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto saveUser(User user);

    UserDto getCurrentUser();
}
