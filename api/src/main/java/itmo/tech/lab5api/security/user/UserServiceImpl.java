package itmo.tech.lab5api.security.user;

import itmo.tech.lab5domain.user.User;
import itmo.tech.lab5domain.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public UserDto saveUser(User user) {
        var savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto getCurrentUser() {
        return (UserDto) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
