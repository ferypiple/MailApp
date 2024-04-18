package group.avantus.mailApp.message.impl.usecase.query;

import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import group.avantus.mailApp.message.repository.impl.jpa.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class FindMessageByStatusQueryTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private FindMessageByStatusQuery findMessageByStatusQuery;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testExecute() {
        Status status = Status.PENDING;


        List<Message> expectedMessages = new ArrayList<>();
        expectedMessages.add(new Message());
        expectedMessages.add(new Message());

        when(messageRepository.findAllByStatus(status)).thenReturn(expectedMessages);


        List<Message> actualMessages = findMessageByStatusQuery.execute(status);


        assertEquals(expectedMessages, actualMessages);
    }
}
