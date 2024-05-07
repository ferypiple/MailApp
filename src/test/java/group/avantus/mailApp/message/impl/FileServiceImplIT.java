package group.avantus.mailApp.message.impl;

import group.avantus.mailApp.message.file.FileService;
import group.avantus.mailApp.message.file.impl.usecase.command.SaveFileCommand;
import group.avantus.mailApp.message.file.impl.usecase.query.GetFileQuery;
import group.avantus.mailApp.message.model.FileEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FileServiceImplIT {

    @Autowired
    private FileService fileService;

    @MockBean
    private SaveFileCommand saveFileCommand;

    @MockBean
    private GetFileQuery getFileQuery;

    @Test
    public void testSaveFile() throws IOException {
        MultipartFile multipartFile = mock(MultipartFile.class);
        byte[] fileData = "Test file content".getBytes();
        when(multipartFile.getOriginalFilename()).thenReturn("test.txt");
        when(multipartFile.getBytes()).thenReturn(fileData);
        FileEntity savedFileEntity = new FileEntity();
        savedFileEntity.setID(1L);
        savedFileEntity.setFileName("test.txt");
        savedFileEntity.setData(fileData);
        when(saveFileCommand.execute(any(FileEntity.class))).thenReturn(savedFileEntity);


        FileEntity result = fileService.saveFile(multipartFile);

        // Assert
        assertEquals(savedFileEntity, result);
        verify(saveFileCommand, times(1)).execute(any(FileEntity.class));
    }

    @Test
    public void testGetFiles() {

        Long messageId = 1L;
        FileEntity file1 = new FileEntity();
        file1.setID(1L);
        file1.setFileName("test1.txt");
        FileEntity file2 = new FileEntity();
        file2.setID(2L);
        file2.setFileName("test2.txt");
        List<FileEntity> expectedFiles = Arrays.asList(file1, file2);
        when(getFileQuery.execute(messageId)).thenReturn(expectedFiles);


        List<FileEntity> result = fileService.getFiles(messageId);


        assertEquals(expectedFiles, result);
        verify(getFileQuery, times(1)).execute(messageId);
    }
}
