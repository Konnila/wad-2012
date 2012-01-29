package wad.varasto.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.varasto.database.VarastoDao;
import wad.varasto.domain.Esine;

@Service
public class SimpleEsinePalvelu implements EsinePalvelu {

    @Autowired
    private VarastoDao varastoDao;

    @Override
    @Transactional
    public void lisaa(Esine esine) {
        varastoDao.create(esine);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Esine> listaa() {
        return varastoDao.list();
    }

    @Override
    @Transactional
    public void poista(int esineId) {
        Esine e = varastoDao.read(esineId);
        if (e != null) {
            varastoDao.delete(e);
        }
    }
}
