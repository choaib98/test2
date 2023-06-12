package ma.wafaimmobilier.sav.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemandeDoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long demandeDocumentId;


    private String libelle;

    private String fileName;

    private String path;

    @ManyToOne
    @JoinColumn(name = "DEMANDE_SAV_ID" ,insertable = false,updatable = false)
    @JsonIgnore
    private DemandeSav demandeSav;

    @Column(name = "DEMANDE_SAV_ID")
    private Long demandeSavId;


}
