package group.avantus.mailApp.message.api.v0.common.model;

import group.avantus.mailApp.message.model.Message;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MessageResponse {

    private final Long id;

    public MessageResponse(Message message) {
        this.id = message.getId();
    }

}
