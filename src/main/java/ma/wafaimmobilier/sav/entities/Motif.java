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
public class Motif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long motifId;
    private String libelle;
    @ManyToOne
    @JoinColumn(name = "OPERATION_ID" ,insertable = false,updatable = false)
    @JsonIgnore
    private Operation operation;
}
