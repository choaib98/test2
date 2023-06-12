package ma.wafaimmobilier.sav.repositories;

import ma.wafaimmobilier.sav.entities.ChargeSav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeSavRepository extends JpaRepository<ChargeSav,Long> {

}
