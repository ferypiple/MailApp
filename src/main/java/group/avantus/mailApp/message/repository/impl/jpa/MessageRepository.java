package group.avantus.mailApp.message.repository.impl.jpa;

import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {

  List<Message> findAllByStatus(Status status);

  Optional<Message> findById(Long id);
}

