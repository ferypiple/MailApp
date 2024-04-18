package group.avantus.mailApp.message.impl.usecase.command;

import group.avantus.mailApp.message.exception.MessageNotFoundException;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import group.avantus.mailApp.message.repository.impl.jpa.MessageRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ChangeStatusCommandTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private ChangeStatusCommand changeStatusCommand;

    @Test
    public void testExecute() {
        Long messageId = 1L;
        Status status = Status.SEND;
        Message message = new Message();
        message.setID(messageId);
        message.setStatus(Status.PENDING);

        when(messageRepository.findById(messageId)).thenReturn(Optional.of(message));
        when(messageRepository.save(any(Message.class))).thenReturn(message);


        Message result = changeStatusCommand.execute(messageId, status);


        assertEquals(status, result.getStatus());
        verify(messageRepository, times(1)).findById(messageId);
        verify(messageRepository, times(1)).save(message);
    }

    @Test
    public void testExecute_MessageNotFound() {

        Long messageId = 1L;
        Status status = Status.PENDING;

        when(messageRepository.findById(messageId)).thenReturn(Optional.empty());


        assertThrows(MessageNotFoundException.class, () -> {
            changeStatusCommand.execute(messageId, status);
        });
    }
}