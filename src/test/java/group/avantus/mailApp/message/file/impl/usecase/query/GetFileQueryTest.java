package group.avantus.mailApp.message.file.impl.usecase.query;

import group.avantus.mailApp.BaseTest;
import group.avantus.mailApp.message.file.repository.impl.jpa.FileRepository;
import group.avantus.mailApp.message.model.FileEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetFileQueryTest extends BaseTest {

    @MockBean
    private FileRepository fileRepository;

    @InjectMocks
    private GetFileQuery getFileQuery;

    @Test
    public void testGetFile() {

        Long messageId = 1L;
        List<FileEntity> expectedFiles = Arrays.asList(new FileEntity(), new FileEntity());
        when(fileRepository.findByMessageId(messageId)).thenReturn(expectedFiles);


        List<FileEntity> actualFiles = getFileQuery.execute(messageId);


        assertEquals(expectedFiles, actualFiles);
    }
}
