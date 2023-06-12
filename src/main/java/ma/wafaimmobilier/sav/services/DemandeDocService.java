package ma.wafaimmobilier.sav.services;

import ma.wafaimmobilier.sav.entities.DemandeDoc;
import ma.wafaimmobilier.sav.entities.DemandeSav;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface DemandeDocService {
    List<DemandeDoc> findAllDemandeDocService();

    DemandeDoc findDemandeDocServiceById(Long demandeDocServiceId);

   // DemandeDoc addDemandeDocService(DemandeDoc demandeDoc);


    void addDemandeDocService(List<DemandeDocDto> demandeDocDtos);

    DemandeDoc updateDemandeDocService(Long demandeDocServiceId, DemandeDoc demandeDoc);

    void deleteDemandeDocService(Long demandeDocServiceId);

    DemandeDoc patchDemandeDocService(Long demandeDocServiceId, Map<String,Object> mapDemandeDocService);

    List<DemandeDocDto> findDemandeDocDtoByDemandeSav(DemandeSav demandeSav);

 //   public void storeDocument( Long operationId, Long documentId,MultipartFile... file) throws IOException;

}
