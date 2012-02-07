package wad.tokkel.models;

import java.io.Serializable;
import javax.persistence.*;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.data.domain.Persistable;

@MappedSuperclass
public class AbstractModel implements Serializable, Persistable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return (id == null);
    }
}
