package group.avantus.mailApp.message.impl;
import group.avantus.mailApp.impl.usecase.command.SaveFileCommand;
import group.avantus.mailApp.message.model.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class FileService {

    @Autowired
    private SaveFileCommand saveFileCommand;

    @Transactional
    public FileEntity saveFile(MultipartFile multipartFile) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(multipartFile.getOriginalFilename());
        fileEntity.setData(multipartFile.getBytes());
        return saveFileCommand.execute(fileEntity);
    }
}
