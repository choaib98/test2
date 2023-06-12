package ma.wafaimmobilier.sav.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dossier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dossierId;
    private String cin;
    private String email;
    private double montantDossier;
    private String crd;
    private double mensualite;
    private Date dateCredit;
    private double total_impaye;
    private String duree;
    private String dureeRestante;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "dossierclient",
            joinColumns = { @JoinColumn(name = "dossierId") },
            inverseJoinColumns = { @JoinColumn(name = "clientId") })
    private List<Client> clients;

    //@OneToMany(mappedBy = "dossier")
    //private List<DemandeSav> demandeSav;

    /*@OneToOne(mappedBy = "dossier")
    private Operation operation;*/


}
