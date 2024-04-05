package group.avantis.email.responses;

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
