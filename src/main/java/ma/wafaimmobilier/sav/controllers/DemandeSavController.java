package ma.wafaimmobilier.sav.controllers;

import ma.wafaimmobilier.sav.entities.DemandeDoc;
import ma.wafaimmobilier.sav.entities.DemandeSav;
import ma.wafaimmobilier.sav.entities.Dossier;
import ma.wafaimmobilier.sav.entities.Operation;
import ma.wafaimmobilier.sav.services.DemandeSavService;
import ma.wafaimmobilier.sav.services.DossierService;
import ma.wafaimmobilier.sav.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sav")
@CrossOrigin("*")
public class DemandeSavController {
    @Autowired
    DemandeSavService demandeSavService;
    @Autowired
    DossierService dossierService;
    @Autowired
    OperationService operationService;

    @GetMapping("demandesav")
    public List<DemandeSav> getAllDemandeSavs(){
        return demandeSavService.findAllDemandeSav();
    }

    @GetMapping("demandesav/{id}")
    public DemandeSav getDemandeSavById(@PathVariable(name="id") Long id){
        DemandeSav demandeSav=demandeSavService.findDemandeSavById(id);
        /*if(operation==null){
            throw new OperationNotFoundException(id);
        }*/
        return demandeSavService.findDemandeSavById(id);
    }

   /* @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("demandesav/{dossierId}/")
    public DemandeSav addDemandeSav(@PathVariable(name = "dossierId") Long dossierId){

        DemandeSav demandeSav=new DemandeSav();
        Dossier dossier=dossierService.findDossierById(dossierId);
        demandeSav.setDossier(dossier);
        return demandeSavService.addDemandeSav(demandeSav);
    }*/


    @PatchMapping(value = "/demandesav/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DemandeSav> patchDemandeSav(@PathVariable(name = "id") Long demandeSavId,
                                                    @RequestBody Map<String, Object> mapDemandeSav) {
        DemandeSav demandeSav=demandeSavService.patchDemandeSav(demandeSavId,mapDemandeSav);
        return ResponseEntity.ok(demandeSav);


    }


    @GetMapping("/demandesav/operation/{operationId}")
    public List<DemandeSav> findDocumentsByOperation(@PathVariable(name = "operationId") Long operationId){

        Operation operation=operationService.findOperationById(operationId);
        return demandeSavService.findDemandeSavByOperation(operation);
    }

   /* @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("demandesav/{dossierId}/{operationId}")
    public DemandeSav ajouterDemandeSav(@PathVariable(name = "dossierId") Long dossierId,@PathVariable(name = "operationId") Long operationId){

        DemandeSav demandeSav1=demandeSavService.ajouterDemandeSav(dossierId,operationId);
        demandeSav1.setDate( new Date());
        System.out.println(demandeSav1.getDate());
        return demandeSav1;
    }*/
}
