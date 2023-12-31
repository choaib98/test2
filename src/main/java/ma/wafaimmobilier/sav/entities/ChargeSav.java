package ma.wafaimmobilier.sav.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChargeSav {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chargeId;
    private String nom;
    private String prenom;
    @OneToMany(targetEntity = DemandeSav.class,mappedBy = "chargeSav",cascade = CascadeType.ALL)

    private List<DemandeSav> demandeSav;
}
