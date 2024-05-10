package group.avantus.mailApp.message.model;

import group.avantus.mailApp.model.BaseModel;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "files")
public class FileEntity implements BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message message;

    @Override
    public Serializable getId() {
        return this.id;
    }

    @Override
    public void setID(Serializable id) {
        this.id = (Long) id;
    }

}