package group.avantus.mailApp.message.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MessageResponse {
    private Integer id;

    @Override
    public String toString() {
        return "id: " + id;


    }
}