package ma.wafaimmobilier.sav.services;

import ma.wafaimmobilier.sav.entities.ChargeSav;
import ma.wafaimmobilier.sav.entities.Client;
import ma.wafaimmobilier.sav.repositories.ChargeSavRepository;
import ma.wafaimmobilier.sav.repositories.ClientRepository;
import ma.wafaimmobilier.sav.utils.PatchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Service
@Transactional
public class ChargeSavServiceImpl implements ChargeSavService{
    @Autowired
    private ChargeSavRepository chargeSavRepository;
    @Autowired
    private ChargeSavService chargeSavService;
    @Autowired
    private PatchHelper patchHelper;

    @Override
    public List<ChargeSav> findAllChargeSavs() {
        return chargeSavRepository.findAll();
    }

    @Override
    public ChargeSav findChargeSavById(Long chargeSavId) {
        return chargeSavRepository.findById(chargeSavId).orElseThrow();
    }

    @Override
    public ChargeSav addChargeSav(ChargeSav chargeSav) {
        return chargeSavRepository.save(chargeSav);
    }

    @Override
    public ChargeSav updateChargeSav(Long chargeSavId, ChargeSav chargeSav) {
       /* clientService.findClientById(clientId);
        client.setClientId(clientId);
        return clientRepository.save(client);*/
        return null;
    }

    @Override
    public void deleteChargeSav(Long chargeSavId) {
        chargeSavRepository.deleteById(chargeSavId);
    }

    @Override
    public ChargeSav patchChargeSav(Long chargeSavId, Map<String, Object> mapChargeSav) {

        ChargeSav chargeSav=chargeSavService.findChargeSavById(chargeSavId);
        ChargeSav patchedChargeSav=patchHelper.mergePatch(mapChargeSav,chargeSav, ChargeSav.class);
        return chargeSavService.updateChargeSav(chargeSavId,patchedChargeSav);
    }
}
