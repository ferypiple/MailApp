package group.avantus.mailApp.message.api.v0.common.model;

import group.avantus.mailApp.message.model.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MessageResponse {

  private Long id;

  public MessageResponse(Message message) {
    this.id = message.getId();
  }

  @Override
  public String toString() {
    return "id: " + id;


  }
}
