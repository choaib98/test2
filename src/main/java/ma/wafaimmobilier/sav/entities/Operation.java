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
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operationId;

    private String nomOperation;

    private String codeOperation;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "operationdoc",
            joinColumns = { @JoinColumn(name = "operationId") },
            inverseJoinColumns = { @JoinColumn(name = "documentId") })
    private List<Document> documents;

    /*@OneToOne(mappedBy = "operation")
    private DemandeSav demandeSav;
*/
    @OneToMany(targetEntity = DemandeSav.class,mappedBy = "operation")
    private List<DemandeSav> demandeSav;
   /* @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dossier_id", referencedColumnName = "dossierId")
    private Dossier dossier;*/
}
