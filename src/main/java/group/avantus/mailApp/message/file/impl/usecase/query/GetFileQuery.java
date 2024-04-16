package group.avantus.mailApp.message.file.impl.usecase.query;

import group.avantus.mailApp.message.model.FileEntity;
import group.avantus.mailApp.message.file.repository.impl.jpa.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetFileQuery {


  private FileRepository fileRepository;

  @Autowired
  public GetFileQuery(FileRepository fileRepository) {
    this.fileRepository = fileRepository;
  }


  public List<FileEntity> execute(Long messageId) {
    return fileRepository.findByMessageId(messageId);
  }
}
