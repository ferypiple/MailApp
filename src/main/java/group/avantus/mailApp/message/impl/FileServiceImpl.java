package group.avantus.mailApp.message.impl;

import group.avantus.mailApp.message.file.FileService;
import group.avantus.mailApp.message.file.impl.usecase.command.SaveFileCommand;
import group.avantus.mailApp.message.file.impl.usecase.query.GetFileQuery;
import group.avantus.mailApp.message.model.FileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {


    private final SaveFileCommand saveFileCommand;
    private final GetFileQuery getFileQuery;

    @Transactional
    public FileEntity saveFile(MultipartFile multipartFile) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(multipartFile.getOriginalFilename());
        fileEntity.setData(multipartFile.getBytes());
        return saveFileCommand.execute(fileEntity);
    }

    @Transactional
    public List<FileEntity> getFiles(Long messageId) {
        return getFileQuery.execute(messageId);
    }
}
