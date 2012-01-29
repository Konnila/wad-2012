package wad.varasto.service;

import java.util.List;
import wad.varasto.domain.Esine;

public interface EsinePalvelu {
    public void lisaa(Esine esine);
    public List<Esine> listaa();
    public void poista(int esineId);
}
