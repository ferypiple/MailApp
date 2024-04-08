package group.avantus.mailApp.message.model;

import group.avantus.mailApp.model.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "messages")
public class Message implements BaseModel<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String from_email;
    @Column(nullable = false)
    private String to_email;
    @Column(nullable = false)
    private String subject;

    @Column(length = 1000,nullable = false)
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDateTime send_date;

    @Override
    public void setID(Integer id) {
        this.id = id;
    }
}
