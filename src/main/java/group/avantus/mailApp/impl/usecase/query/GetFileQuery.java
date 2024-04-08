package group.avantus.mailApp.impl.usecase.query;

import group.avantus.mailApp.message.impl.FileService;
import group.avantus.mailApp.message.model.FileEntity;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.repository.impl.jpa.FileRepository;
import group.avantus.mailApp.message.repository.impl.jpa.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GetFileQuery {


    @Autowired
    private FileRepository fileRepository;


    @Transactional(readOnly = true)
    public List<FileEntity> execute(Long messageId) {
        return fileRepository.findByMessageId(messageId);
    }
}
