package group.avantus.mailApp.message.api.v0.common.model;


import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StatusResponse {

  private Status status;

  @Override
  public String toString() {
    return "STATUS: " + status.toString();


  }
}