package group.avantus.mailApp.message.impl;

import group.avantus.mailApp.message.MessageService;
import group.avantus.mailApp.message.exception.MessageNotCreate;
import group.avantus.mailApp.message.impl.usecase.command.ChangeStatusCommand;
import group.avantus.mailApp.message.impl.usecase.command.SaveMessageCommand;
import group.avantus.mailApp.message.impl.usecase.query.GetMessageQuery;
import group.avantus.mailApp.message.model.MailRecord;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MessageServiceImplIT {

    @Autowired
    private MessageService messageService;

    @MockBean
    private SaveMessageCommand saveMessageCommand;

    @MockBean
    private GetMessageQuery getMessageQuery;

    @MockBean
    private ChangeStatusCommand changeStatusCommand;

    @Test
    public void testSaveMessage() throws MessageNotCreate, IOException {
        String from = "sender@example.com";
        String to = "recipient@example.com";
        String subject = "Test Message";
        String text = "This is a test message body";
        MailRecord mailRecord = new MailRecord(from, to, subject, text, null);


        Message mockMessageEntity = new Message();
        mockMessageEntity.setID(1L);
        Mockito.when(saveMessageCommand.execute(mailRecord)).thenReturn(mockMessageEntity);


        Message savedMessage = messageService.saveMessage(mailRecord);


        assertNotNull(savedMessage);
        assertEquals(mockMessageEntity.getId(), savedMessage.getId());
    }

    @Test
    public void testSaveMessage_Exception() throws MessageNotCreate, IOException {
        String from = "sender@example.com";
        String to = "recipient@example.com";
        String subject = "Test Message";
        String text = "This is a test message body";
        MailRecord mailRecord = new MailRecord(from, to, subject, text, null);


        Mockito.when(saveMessageCommand.execute(mailRecord)).thenThrow(new RuntimeException("Message exception"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            messageService.saveMessage(mailRecord);
        });

        String expectedMessage = "Message exception";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGetMessage() {
        Long messageId = 1L;
        Message mockMessageEntity = new Message();
        mockMessageEntity.setID(messageId);
        Mockito.when(getMessageQuery.execute(messageId)).thenReturn(mockMessageEntity);


        Message retrievedMessage = messageService.getMessage(messageId);


        assertNotNull(retrievedMessage);
        assertEquals(mockMessageEntity.getId(), retrievedMessage.getId());
    }

    @Test
    public void testUpdateStatus() {
        Long messageId = 1L;
        Message mockMessageEntity = new Message();
        mockMessageEntity.setID(messageId);
        mockMessageEntity.setStatus(Status.SEND);
        Mockito.when(changeStatusCommand.execute(messageId, Status.SEND)).thenReturn(mockMessageEntity);


        Message updatedMessage = messageService.updateStatus(messageId, Status.SEND);


        assertNotNull(updatedMessage);
        assertEquals(Status.SEND, updatedMessage.getStatus());
    }
}
