package group.avantis.email;

import group.avantis.email.model.Message;
import org.springframework.stereotype.Service;


@Service
public interface EmailService {


    void sendEmail(Message message);
}
