package ma.wafaimmobilier.sav.services;

import ma.wafaimmobilier.sav.entities.Client;
import ma.wafaimmobilier.sav.entities.DemandeSav;
import ma.wafaimmobilier.sav.entities.Operation;

import java.util.List;
import java.util.Map;

public interface DemandeSavService {
    List<DemandeSav> findAllDemandeSav();

    DemandeSav findDemandeSavById(Long demandeSavId);

  //  DemandeSav addDemandeSav(DemandeSav demandeSav);

    DemandeSav updateDemandeSav(Long demandeSavId, DemandeSav demandeSav);

    void deleteDemandeSav(Long demandeSavId);

    DemandeSav patchDemandeSav(Long demandeSavId, Map<String,Object> mapDemandeSavId);

    DemandeSav addDemandeSav(Long dossierId,Long operationId);

    List<DemandeSav> findDemandeSavByOperation(Operation operation);
}
