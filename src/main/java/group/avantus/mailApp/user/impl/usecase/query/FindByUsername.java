package group.avantus.mailApp.user.impl.usecase.query;

import group.avantus.mailApp.user.model.User;
import group.avantus.mailApp.user.repository.impl.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class FindByUsername {

    private final UserRepository userRepository;


    public Optional<User> execute(String username) {
        return userRepository.findByUsername(username);
    }

}
