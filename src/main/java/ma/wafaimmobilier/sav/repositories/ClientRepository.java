package ma.wafaimmobilier.sav.repositories;

import ma.wafaimmobilier.sav.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
}
