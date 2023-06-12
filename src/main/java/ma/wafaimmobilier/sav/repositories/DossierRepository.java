package ma.wafaimmobilier.sav.repositories;

import ma.wafaimmobilier.sav.entities.DemandeSav;
import ma.wafaimmobilier.sav.entities.Dossier;
import ma.wafaimmobilier.sav.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DossierRepository extends JpaRepository<Dossier,Long> {
    List<Dossier> findByDossierIdOrCinOrEmail(Optional<Long> dossierId, Optional<String> cin, Optional<String> email);
    //Dossier  findDossierByDemandeSav(DemandeSav demandeSav);

}
