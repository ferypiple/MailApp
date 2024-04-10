package group.avantus.mailApp.message.file.impl.usecase.command;

import group.avantus.mailApp.message.model.FileEntity;
import group.avantus.mailApp.message.repository.impl.jpa.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveFileCommand {

    private final FileRepository fileRepository;

    @Autowired
    public SaveFileCommand(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileEntity execute(FileEntity fileEntity) {
        return fileRepository.save(fileEntity);
    }
}
