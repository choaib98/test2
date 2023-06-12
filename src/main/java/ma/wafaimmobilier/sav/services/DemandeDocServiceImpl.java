package ma.wafaimmobilier.sav.services;

import ma.wafaimmobilier.sav.entities.DemandeDoc;
import ma.wafaimmobilier.sav.entities.DemandeSav;
import ma.wafaimmobilier.sav.repositories.DemandeDocRepository;
import ma.wafaimmobilier.sav.utils.PatchHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DemandeDocServiceImpl implements DemandeDocService{
    @Autowired
    private DemandeDocRepository demandeDocRepository;
    @Autowired
    private DemandeDocService demandeDocService;
    @Autowired
    private PatchHelper patchHelper;
    @Autowired
    private OperationService operationService;
    @Autowired
    private DocumentService documentService;
    @Override
    public List<DemandeDoc> findAllDemandeDocService() {
        return demandeDocRepository.findAll();
    }

    @Override
    public DemandeDoc findDemandeDocServiceById(Long demandeDocServiceId) {
        return demandeDocRepository.findById(demandeDocServiceId).orElseThrow();
    }

    @Override
    public void addDemandeDocService(List<DemandeDocDto> demandeDocDtos) {
        DemandeDoc demandeDoc=null;

        List<DemandeDoc> demandeDocs=new ArrayList<>();
        for (DemandeDocDto demande: demandeDocDtos) {
            demandeDoc=new DemandeDoc();
            BeanUtils.copyProperties(demande,demandeDoc);
            demandeDoc.setDemandeSavId(demande.getIdDemandeSav());
            demandeDocs.add(demandeDoc);
        }

        demandeDocRepository.saveAll(demandeDocs);

    }


    @Override
    public DemandeDoc updateDemandeDocService(Long demandeDocId, DemandeDoc demandeDoc) {
        demandeDocService.findDemandeDocServiceById(demandeDocId);
        demandeDoc.setDemandeDocumentId(demandeDocId);
        return demandeDocRepository.save(demandeDoc);
    }

    @Override
    public void deleteDemandeDocService(Long demandeDocServiceId) {
        demandeDocRepository.deleteById(demandeDocServiceId);
    }

    @Override
    public DemandeDoc patchDemandeDocService(Long demandeDocServiceId, Map<String, Object> mapDemandeDocService) {

        DemandeDoc demandeDoc=demandeDocService.findDemandeDocServiceById(demandeDocServiceId);
        DemandeDoc patchedDemandeDoc=patchHelper.mergePatch(mapDemandeDocService,demandeDoc, DemandeDoc.class);
        return demandeDocService.updateDemandeDocService(demandeDocServiceId,patchedDemandeDoc);
    }

    @Override
    public List<DemandeDocDto> findDemandeDocDtoByDemandeSav(DemandeSav demandeSav) {
        return demandeDocRepository.findDemandeDocDtoByDemandeSav(demandeSav);
    }

  /*  @Override
    public void storeDocument( Long operationId, Long documentId,MultipartFile... files) throws IOException {
        Operation operation=operationService.findOperationById(operationId);

        for(MultipartFile f:files){
            Document document =documentService.findDocumentById(documentId);
            DemandeDoc demandeDoc=new DemandeDoc();
            demandeDoc.setData(f.getBytes());
            demandeDoc.setDocument(document);
            demandeDocRepository.save(demandeDoc);
        }

        // demandeDoc.setOperation(operation);
        // document.setFileName(file.getOriginalFilename());
        // document.setData(file.getBytes());
        // document.setOperation(operation);

    }*/


}
