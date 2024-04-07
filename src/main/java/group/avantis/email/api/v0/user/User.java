package group.avantis.email.api.v0.user;

import group.avantis.email.model.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements BaseModel<Integer> {
    @Id
    private Integer id;


    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setID(Integer id) {
        this.id = id;
    }
}
