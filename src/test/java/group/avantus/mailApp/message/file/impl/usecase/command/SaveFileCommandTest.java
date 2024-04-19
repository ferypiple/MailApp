package group.avantus.mailApp.message.file.impl.usecase.command;

import group.avantus.mailApp.BaseTest;
import group.avantus.mailApp.message.file.repository.impl.jpa.FileRepository;
import group.avantus.mailApp.message.model.FileEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SaveFileCommandTest extends BaseTest {

    @MockBean
    private FileRepository fileRepository;

    @InjectMocks
    private SaveFileCommand saveFileCommand;


    @Test
    public void testSave() {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setID(1L);
        fileEntity.setFileName("File");


        when(fileRepository.save(fileEntity)).thenReturn(fileEntity);


        FileEntity result = saveFileCommand.execute(fileEntity);


        verify(fileRepository).save(fileEntity);


        assertEquals(fileEntity, result);
    }

}
