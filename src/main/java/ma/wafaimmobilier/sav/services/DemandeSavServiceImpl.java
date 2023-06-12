package ma.wafaimmobilier.sav.services;

import ma.wafaimmobilier.sav.entities.*;
import ma.wafaimmobilier.sav.repositories.DemandeDocRepository;
import ma.wafaimmobilier.sav.repositories.DemandeSavRepository;
import ma.wafaimmobilier.sav.utils.PatchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DemandeSavServiceImpl implements DemandeSavService{
    @Autowired
    private DemandeSavService demandeSavService;
    @Autowired
    private DemandeSavRepository demandeSavRepository;
    @Autowired
    private PatchHelper patchHelper;

    @Override
    public List<DemandeSav> findAllDemandeSav() {
        return demandeSavRepository.findAll();
    }

    @Override
    public DemandeSav findDemandeSavById(Long demandeSavId) {
        return demandeSavRepository.findById(demandeSavId).orElseThrow();
    }



    @Override
    public DemandeSav updateDemandeSav(Long demandeSavId, DemandeSav demandeSav) {
        demandeSavService.findDemandeSavById(demandeSavId);
        demandeSav.setDemandeSavId(demandeSavId);
        return demandeSavRepository.save(demandeSav);
    }

    @Override
    public void deleteDemandeSav(Long demandeSavId) {
        demandeSavRepository.deleteById(demandeSavId);
    }

    @Override
    public DemandeSav patchDemandeSav(Long demandeSavId, Map<String, Object> mapDemandeSavId) {
        DemandeSav demandeSav=demandeSavService.findDemandeSavById(demandeSavId);
        DemandeSav patchedDemandeSav=patchHelper.mergePatch(mapDemandeSavId,demandeSav, DemandeSav.class);
        return demandeSavService.updateDemandeSav(demandeSavId,patchedDemandeSav);
    }

    @Override
    public DemandeSav addDemandeSav(Long dossierId, Long operationId) {

        DemandeSav demandeSav=new DemandeSav();
        demandeSav.setDossierId(dossierId);
        demandeSav.setOperationId(operationId);
        demandeSav.setDate(new Date());

        return demandeSavRepository.save(demandeSav);
    }

    @Override
    public List<DemandeSav> findDemandeSavByOperation(Operation operation) {
        return demandeSavRepository.findDemandeSavByOperation(operation);
    }


}
