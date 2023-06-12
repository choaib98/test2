package ma.wafaimmobilier.sav.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemandeDocDto {


    private String libelle;

    private String path;

    private String fileName;

    private Long idDemandeSav;

}
