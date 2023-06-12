package ma.wafaimmobilier.sav.repositories;

import ma.wafaimmobilier.sav.entities.DemandeSav;
import ma.wafaimmobilier.sav.entities.Operation;
import ma.wafaimmobilier.sav.services.DemandeDocDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeSavRepository extends JpaRepository<DemandeSav,Long> {
    List<DemandeSav> findDemandeSavByOperation(Operation operation);

}
