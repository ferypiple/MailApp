package group.avantus.mailApp.message.file.impl.usecase.query;

import group.avantus.mailApp.message.file.repository.impl.jpa.FileRepository;
import group.avantus.mailApp.message.model.FileEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GetFileQueryTest {

    @Mock
    private FileRepository fileRepository;

    @InjectMocks
    private GetFileQuery getFileQuery;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecute() {

        Long messageId = 1L;
        List<FileEntity> expectedFiles = Arrays.asList(new FileEntity(), new FileEntity());
        when(fileRepository.findByMessageId(messageId)).thenReturn(expectedFiles);


        List<FileEntity> actualFiles = getFileQuery.execute(messageId);


        assertEquals(expectedFiles, actualFiles);
    }
}
