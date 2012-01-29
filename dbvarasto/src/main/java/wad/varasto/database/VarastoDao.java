package wad.varasto.database;

import java.util.List;
import wad.varasto.domain.Esine;

public interface VarastoDao extends DAO<Esine> {
    public List<Esine> list();
}
