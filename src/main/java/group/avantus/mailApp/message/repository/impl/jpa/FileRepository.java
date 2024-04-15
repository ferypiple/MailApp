package group.avantus.mailApp.message.repository.impl.jpa;

import group.avantus.mailApp.message.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface FileRepository extends JpaRepository<FileEntity, Long> {

  List<FileEntity> findByMessageId(Long messageId);
}
