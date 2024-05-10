package group.avantus.mailApp.message.api.v0.common.model;


import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class StatusResponse {

    private final Status status;

    public StatusResponse(Message message) {
        this.status = message.getStatus();
    }
}