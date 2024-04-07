package group.avantis.email.repsitory.impl.jpa;

import group.avantis.email.model.Message;
import group.avantis.email.model.Status;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByStatus(Status status);
}
