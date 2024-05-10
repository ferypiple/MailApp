package group.avantus.mailApp.user;


import group.avantus.mailApp.user.dto.RegistrationUserDto;
import group.avantus.mailApp.user.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> findByUsername(String username);

    User createNewUser(RegistrationUserDto registrationUserDto);
}