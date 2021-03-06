package wad.varasto.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Esine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String nimi;
    private Double paino;

    public Esine() {
    }

    public int getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public Double getPaino() {
        return paino;
    }

    public void setPaino(Double paino) {
        this.paino = paino;
    }
}
