package group.avantis.email.model;

import java.io.Serializable;

public interface BaseModel<T extends Serializable> {
    T getId();
    void setID(T id);
}
