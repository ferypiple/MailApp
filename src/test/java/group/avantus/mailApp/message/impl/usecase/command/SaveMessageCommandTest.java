package group.avantus.mailApp.message.impl.usecase.command;

import group.avantus.mailApp.message.file.impl.usecase.command.SaveFileCommand;
import group.avantus.mailApp.message.file.impl.usecase.query.GetFileQuery;
import group.avantus.mailApp.message.impl.FileServiceImpl;
import group.avantus.mailApp.message.model.FileEntity;
import group.avantus.mailApp.message.model.MailRecord;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import group.avantus.mailApp.message.repository.impl.jpa.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class SaveMessageCommandTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private SaveFileCommand saveFileCommand;

    @Mock
    private GetFileQuery getFileQuery;

    private FileServiceImpl fileService;

    private SaveMessageCommand saveMessageCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        fileService = new FileServiceImpl(saveFileCommand, getFileQuery);
        saveMessageCommand = new SaveMessageCommand(messageRepository, fileService);
    }

    @Test
    void testExecute() throws IOException {
        MailRecord mailRecord = new MailRecord("sender@example.com", "recipient@example.com",
                "Test Subject", "Test Body", new MultipartFile[0]);
        List<FileEntity> files = new ArrayList<>();
        files.add(FileEntity.builder()
                .fileName("example.txt")
                .data("file_content".getBytes())
                .build());


        when(saveFileCommand.execute(any(FileEntity.class))).thenReturn(files.get(0));


        when(messageRepository.save(any(Message.class))).thenReturn(new Message(1L,
                "sender@example.com", "recipient@example.com", "Test Subject",
                "Test Body", Status.PENDING, LocalDateTime.now(), files));


        Message savedMessage = saveMessageCommand.execute(mailRecord);


        verify(saveFileCommand, times(mailRecord.attachments().length)).execute(any(FileEntity.class));
        verify(messageRepository, times(1)).save(any(Message.class));


        assertEquals(mailRecord.from(), savedMessage.getFrom_email());
        assertEquals(mailRecord.to(), savedMessage.getTo_email());
        assertEquals(Status.PENDING, savedMessage.getStatus());
        assertEquals(mailRecord.subject(), savedMessage.getSubject());
        assertEquals(mailRecord.text(), savedMessage.getText());
        assertEquals(files, savedMessage.getFiles());
    }
}

