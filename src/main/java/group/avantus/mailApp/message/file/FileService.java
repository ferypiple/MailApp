package group.avantus.mailApp.message.file;

import group.avantus.mailApp.message.model.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    public FileEntity saveFile(MultipartFile multipartFile) throws IOException;
}
