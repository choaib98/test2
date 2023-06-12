package ma.wafaimmobilier.sav.services;

import ma.wafaimmobilier.sav.entities.DemandeSav;
import ma.wafaimmobilier.sav.entities.Dossier;
import ma.wafaimmobilier.sav.repositories.DossierRepository;
import ma.wafaimmobilier.sav.utils.PatchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class DossierServiceImpl implements DossierService{
    @Autowired
    private DossierService dossierService;
    @Autowired
    private DossierRepository dossierRepository;
    @Autowired
    private PatchHelper patchHelper;
    @Override
    public List<Dossier> findAllDossiers() {
        return dossierRepository.findAll();
    }

    @Override
    public Dossier findDossierById(Long dossierId) {
        return dossierRepository.findById(dossierId).orElseThrow();
    }

    @Override
    public Dossier addDossier(Dossier dossier) {
        return dossierRepository.save(dossier);
    }

    @Override
    public Dossier updateDossier(Long dossierId, Dossier dossier) {
        dossierService.findDossierById(dossierId);
        dossier.setDossierId(dossierId);
        return dossierRepository.save(dossier);
    }

    @Override
    public void deleteDossier(Long dossierId) {
        dossierRepository.deleteById(dossierId);
    }

    @Override
    public Dossier DemandeSav(DemandeSav demandeSav) {
        return null;
    }
/*
    @Override
    public Dossier DemandeSav(DemandeSav demandeSav) {
        return dossierRepository.findDossierByDemandeSav(demandeSav);
    }*/

    @Override
    public Dossier patchDossier(Long dossierId, Map<String, Object> mapDossier) {
        Dossier dossier=dossierService.findDossierById(dossierId);
        Dossier patchedOperation=patchHelper.mergePatch(mapDossier,dossier, Dossier.class);
        return dossierService.updateDossier(dossierId,patchedOperation);
    }

    @Override
    public List<Dossier> findByDossierIdOrCinOrEmail(Optional<Long> dossierId, Optional<String> cin, Optional<String> email) {
        return dossierRepository.findByDossierIdOrCinOrEmail(dossierId,cin,email);
    }

    /*@Override
    public Dossier findDossierByDemandeSav(DemandeSav demandeSav) {
        return dossierRepository.findDossierByDemandeSav(demandeSav);
    }*/
}
