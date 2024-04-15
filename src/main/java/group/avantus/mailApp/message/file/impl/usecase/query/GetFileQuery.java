package group.avantus.mailApp.message.file.impl.usecase.query;

import group.avantus.mailApp.message.model.FileEntity;
import group.avantus.mailApp.message.repository.impl.jpa.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GetFileQuery {


    @Autowired
    private FileRepository fileRepository;


    public List<FileEntity> execute(Long messageId) {
        return fileRepository.findByMessageId(messageId);
    }
}
