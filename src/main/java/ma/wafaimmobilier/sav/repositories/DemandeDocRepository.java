package ma.wafaimmobilier.sav.repositories;

import ma.wafaimmobilier.sav.entities.DemandeDoc;
import ma.wafaimmobilier.sav.entities.DemandeSav;
import ma.wafaimmobilier.sav.services.DemandeDocDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeDocRepository extends JpaRepository<DemandeDoc,Long> {
    List<DemandeDocDto> findDemandeDocDtoByDemandeSav(DemandeSav demandeSav);
}
