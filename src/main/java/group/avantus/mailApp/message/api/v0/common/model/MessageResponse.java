package group.avantus.mailApp.message.api.v0.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MessageResponse {
    private Long id;

    @Override
    public String toString() {
        return "id: " + id;


    }
}
