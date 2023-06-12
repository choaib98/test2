package ma.wafaimmobilier.sav.repositories;

import ma.wafaimmobilier.sav.entities.Document;
import ma.wafaimmobilier.sav.entities.Motif;
import ma.wafaimmobilier.sav.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotifRepository extends JpaRepository<Motif,Long> {
    List<Motif> findMotifByOperation(Operation operation);

}
