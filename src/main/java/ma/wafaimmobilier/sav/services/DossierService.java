package ma.wafaimmobilier.sav.services;

import ma.wafaimmobilier.sav.entities.Client;
import ma.wafaimmobilier.sav.entities.DemandeSav;
import ma.wafaimmobilier.sav.entities.Dossier;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DossierService {
    List<Dossier> findAllDossiers();

    Dossier findDossierById(Long dossierId);

    Dossier addDossier(Dossier  dossier);

    Dossier updateDossier(Long dossierId, Dossier dossier);

    void deleteDossier(Long dossierId);

    Dossier DemandeSav(DemandeSav demandeSav);

    Dossier patchDossier(Long dossierId, Map<String,Object> mapDossier);

    List<Dossier>findByDossierIdOrCinOrEmail(Optional<Long> dossierId, Optional<String> cin, Optional<String> email);

  //  Dossier  findDossierByDemandeSav(DemandeSav demandeSav);
}
