package group.avantus.mailApp.user.impl;


import group.avantus.mailApp.role.RoleService;
import group.avantus.mailApp.user.UserService;
import group.avantus.mailApp.user.dto.RegistrationUserDto;
import group.avantus.mailApp.user.impl.usecase.command.SaveUserCommand;
import group.avantus.mailApp.user.impl.usecase.query.FindByUsername;
import group.avantus.mailApp.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final SaveUserCommand saveUserCommand;
    private final FindByUsername findByUsername;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String username) {
        return findByUsername.execute(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername.execute(username).orElseThrow(() -> new UsernameNotFoundException(username)
        );
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public User createNewUser(RegistrationUserDto registrationUserDto) {
        User user = new User();
        user.setUsername(registrationUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        user.setRoles(List.of(roleService.getUserRole()));
        return saveUserCommand.execute(user);
    }

}