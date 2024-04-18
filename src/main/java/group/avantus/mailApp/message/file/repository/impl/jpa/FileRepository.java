package group.avantus.mailApp.message.file.repository.impl.jpa;

import group.avantus.mailApp.message.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FileRepository extends JpaRepository<FileEntity, Long> {

    List<FileEntity> findByMessageId(Long messageId);
}
