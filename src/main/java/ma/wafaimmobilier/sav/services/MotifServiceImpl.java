package ma.wafaimmobilier.sav.services;

import ma.wafaimmobilier.sav.entities.Motif;
import ma.wafaimmobilier.sav.entities.Operation;
import ma.wafaimmobilier.sav.repositories.MotifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class MotifServiceImpl implements  MotifService{
    @Autowired
    private MotifRepository motifRepository;

    @Override
    public List<Motif> findAllMotifs() {
        return motifRepository.findAll();
    }

    @Override
    public Motif findMotifById(Long motifId) {
        return motifRepository.findById(motifId).orElseThrow();
    }

    @Override
    public Motif addMotif(Motif motif) {
        return motifRepository.save(motif);
    }

    @Override
    public Motif updateMotif(Long motifId, Motif motif) {
        return null;
    }

    @Override
    public void deleteMotif(Long motifId) {

    }

    @Override
    public List<Motif> findMotifByOperation(Operation operation) {
        return motifRepository.findMotifByOperation(operation);
    }
}
