package ma.wafaimmobilier.sav.controllers;

import ma.wafaimmobilier.sav.entities.Client;
import ma.wafaimmobilier.sav.entities.DemandeSav;
import ma.wafaimmobilier.sav.entities.Dossier;
import ma.wafaimmobilier.sav.entities.Operation;
import ma.wafaimmobilier.sav.exceptions.DossierNotFoundException;
import ma.wafaimmobilier.sav.services.DemandeSavService;
import ma.wafaimmobilier.sav.services.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("api/sav/dossiers")
@CrossOrigin("*")
public class DossierController {
    @Autowired
    private DossierService dossierService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DemandeSavService demandeSavService;


    @GetMapping("")
    public List<Dossier> getAllDossiers(){


        return dossierService.findAllDossiers();


    }


    @GetMapping("dossier/{id}")
    public Dossier getDossierById(@PathVariable(name="id") Long id){
        Dossier dossier=dossierService.findDossierById(id);
       if(dossier==null){
            throw new DossierNotFoundException(id);
        }
        return dossierService.findDossierById(id);
    }



    @PostMapping("dossiers")
    public Dossier addDossier(@RequestBody Dossier dossier){
        return dossierService.addDossier(dossier);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping(value = "/dossier/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dossier> patchOperation(@PathVariable(name = "id") Long dossierId,
                                                  @RequestBody Map<String, Object> mapOperation) {
        Dossier dossier=dossierService.patchDossier(dossierId,mapOperation);
        return ResponseEntity.ok(dossier);
    }


    @GetMapping("dossier/dossierIdorcinoremail")
    public ResponseEntity <List<Dossier>>getByDossierIdOrCinOrEmail(@RequestParam Optional<Long> dossierId,
                                                                    @RequestParam Optional<String> cin,
                                                                    @RequestParam Optional<String> email){
        return new ResponseEntity<List<Dossier>>(dossierService.findByDossierIdOrCinOrEmail(dossierId,cin,email), HttpStatus.OK);
    }

   /* @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/demandesav/dossier/{dossierId}")
    public Dossier findDocumentsByOperation(@PathVariable(name = "dossierId") Long dossierId){

        DemandeSav demandeSav=demandeSavService.findDemandeSavById(dossierId);
        return dossierService.findDossierByDemandeSav(demandeSav);
    }*/


    @GetMapping("dossier/client")
    public ResponseEntity <List<Dossier>>getByClientNom(@RequestParam String nom
    ){
        //  List<Map<String,Object>> dossiers=jdbcTemplate.queryForList();

        String sql="SELECT * FROM DOSSIER INNER JOIN DOSSIERCLIENT ON DOSSIER.DOSSIER_ID = DOSSIERCLIENT.DOSSIER_ID INNER JOIN CLIENT ON DOSSIERCLIENT.DOSSIER_ID = CLIENT.CLIENT_ID WHERE  CLIENT.PRENOM LIKE "+"'%"+nom+"%'"+ " OR CLIENT.NOM LIKE "+"'%"+nom+"%'"+";";



        List<Dossier> dossiers1 = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Dossier(
                        rs.getLong("dossier_Id"),
                        rs.getString("cin"),
                        rs.getString("email"),
                        rs.getDouble("montant_Dossier"),
                        rs.getString("crd"),
                        rs.getDouble("mensualite"),
                        (Date) rs.getDate("date_Credit"),
                        rs.getDouble("total_impaye"),
                        rs.getString("duree"),
                        rs.getString("duree_Restante"),
                        null



                )
        );

        return new ResponseEntity<>(dossiers1,HttpStatus.OK);
    }

}
