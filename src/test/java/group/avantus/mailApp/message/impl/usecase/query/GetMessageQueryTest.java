package group.avantus.mailApp.message.impl.usecase.query;

import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import group.avantus.mailApp.message.repository.impl.jpa.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GetMessageQueryTest {

    @Mock
    private MessageRepository messageRepository;

    private GetMessageQuery getMessageQuery;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getMessageQuery = new GetMessageQuery(messageRepository);
    }

    @Test
    public void testExecute() {

        Long messageId = 1L;
        Message message = new Message();
        message.setID(messageId);
        message.setSubject("Test Subject");
        message.setStatus(Status.PENDING);

        when(messageRepository.findById(anyLong())).thenReturn(Optional.of(message));

        Optional<Message> result = getMessageQuery.execute(messageId);

        assertEquals(message, result.orElse(null));
    }

    @Test
    public void testExecuteMessageNotFound() {
        Long messageId = 1L;

        when(messageRepository.findById(anyLong())).thenReturn(Optional.empty());


        Optional<Message> result = getMessageQuery.execute(messageId);

        assertEquals(Optional.empty(), result);
    }
}
