package net.coolcoders.showcase.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 * 
 */
@MappedSuperclass
public class AbstractBaseEntity implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractBaseEntity that = (AbstractBaseEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
