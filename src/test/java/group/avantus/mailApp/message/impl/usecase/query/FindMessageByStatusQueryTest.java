package group.avantus.mailApp.message.impl.usecase.query;

import group.avantus.mailApp.BaseTest;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import group.avantus.mailApp.message.repository.impl.jpa.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindMessageByStatusQueryTest extends BaseTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private FindMessageByStatusQuery findMessageByStatusQuery;


    @Test
    void testFindMessage() {
        Status status = Status.PENDING;


        List<Message> expectedMessages = new ArrayList<>();
        expectedMessages.add(new Message());
        expectedMessages.add(new Message());

        when(messageRepository.findAllByStatus(status)).thenReturn(expectedMessages);


        List<Message> actualMessages = findMessageByStatusQuery.execute(status);


        assertEquals(expectedMessages, actualMessages);
    }
}
