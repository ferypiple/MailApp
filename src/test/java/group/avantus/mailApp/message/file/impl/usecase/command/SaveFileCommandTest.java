package group.avantus.mailApp.message.file.impl.usecase.command;

import group.avantus.mailApp.message.file.repository.impl.jpa.FileRepository;
import group.avantus.mailApp.message.model.FileEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SaveFileCommandTest {

    @Mock
    private FileRepository fileRepository;

    @InjectMocks
    private SaveFileCommand saveFileCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecute() {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setID(1L);
        fileEntity.setFileName("File");


        when(fileRepository.save(fileEntity)).thenReturn(fileEntity);


        FileEntity result = saveFileCommand.execute(fileEntity);


        verify(fileRepository).save(fileEntity);


        assertEquals(fileEntity, result);
    }

}
