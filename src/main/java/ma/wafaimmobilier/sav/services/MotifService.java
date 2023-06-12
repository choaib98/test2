package ma.wafaimmobilier.sav.services;

import ma.wafaimmobilier.sav.entities.Dossier;
import ma.wafaimmobilier.sav.entities.Motif;
import ma.wafaimmobilier.sav.entities.Operation;

import java.util.List;

public interface MotifService {
    List<Motif> findAllMotifs();

    Motif findMotifById(Long motifId);

    Motif addMotif(Motif  motif);

    Motif updateMotif(Long motifId, Motif motif);

    void deleteMotif(Long motifId);

    List<Motif> findMotifByOperation(Operation operation);


}
