package ma.wafaimmobilier.sav.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class DemandeSav {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long demandeSavId;

    @ManyToOne()
    @JoinColumn(name = "dossier_id", referencedColumnName = "dossierId" ,insertable = false,updatable = false)
    private Dossier dossier;

    @Temporal(TemporalType.DATE)
    private Date date;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "operation_id", referencedColumnName = "operationId")
    private  Operation operation;*/

    @ManyToOne
    @JoinColumn(name = "OPERATION_ID",insertable = false,updatable = false)
    @JsonIgnore
    private Operation operation;

    @Column(name="OPERATION_ID")
    private Long operationId;
    @Column(name="DOSSIER_ID")
    private Long dossierId;




    @OneToMany(targetEntity = DemandeDoc.class,mappedBy = "demandeSav",cascade = CascadeType.ALL)

    private List<DemandeDoc> demandeDocs;

    @ManyToOne
    @JoinColumn(name = "chargeSavId")
    @JsonIgnore
    private ChargeSav chargeSav;
}
