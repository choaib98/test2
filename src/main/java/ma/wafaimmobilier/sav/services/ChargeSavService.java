package ma.wafaimmobilier.sav.services;

import ma.wafaimmobilier.sav.entities.ChargeSav;
import ma.wafaimmobilier.sav.entities.Client;

import java.util.List;
import java.util.Map;

public interface ChargeSavService {
    List<ChargeSav> findAllChargeSavs();

    ChargeSav findChargeSavById(Long chargeSavId);

    ChargeSav addChargeSav(ChargeSav chargeSav);

    ChargeSav updateChargeSav(Long chargeSavId, ChargeSav chargeSav);

    void deleteChargeSav(Long chargeSavId);

    ChargeSav patchChargeSav(Long chargeSavId, Map<String,Object> mapChargeSav);
}
