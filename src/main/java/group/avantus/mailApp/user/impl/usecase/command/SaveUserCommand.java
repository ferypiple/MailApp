package group.avantus.mailApp.user.impl.usecase.command;

import group.avantus.mailApp.user.model.User;
import group.avantus.mailApp.user.repository.impl.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SaveUserCommand {


    private final UserRepository userRepository;


    public User execute(User user) {
        return userRepository.save(user);
    }

}
