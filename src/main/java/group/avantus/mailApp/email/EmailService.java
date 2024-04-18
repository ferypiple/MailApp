package group.avantus.mailApp.email;

import group.avantus.mailApp.message.model.Message;
import org.springframework.stereotype.Service;


@Service
public interface EmailService {


    void sendEmail(Message message);
}
