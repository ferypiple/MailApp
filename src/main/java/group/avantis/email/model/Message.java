package group.avantis.email.model;

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

    private String from_email;

    private String to_email;

    private String subject;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime send_date;

    @Override
    public void setID(Integer id) {
        this.id = id;
    }
}
