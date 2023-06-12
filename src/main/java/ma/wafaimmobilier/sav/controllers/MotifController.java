package ma.wafaimmobilier.sav.controllers;

import ma.wafaimmobilier.sav.entities.Client;
import ma.wafaimmobilier.sav.entities.Document;
import ma.wafaimmobilier.sav.entities.Motif;
import ma.wafaimmobilier.sav.entities.Operation;
import ma.wafaimmobilier.sav.services.ClientService;
import ma.wafaimmobilier.sav.services.MotifService;
import ma.wafaimmobilier.sav.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sav/")
@CrossOrigin("*")
public class MotifController {
    @Autowired
    MotifService motifService;
    @Autowired
    OperationService operationService;


    @GetMapping("motifs")
    public List<Motif> getAllMotifs(){
        return motifService.findAllMotifs();
    }


    @GetMapping("motif/{id}")
    public Motif getMotifById(@PathVariable(name="id") Long id){
        Motif motif=motifService.findMotifById(id);
        /*if(operation==null){
            throw new OperationNotFoundException(id);
        }*/
        return motif;
    }


    @PostMapping("motifs")
    public Motif addMotif(@RequestBody Motif motif){
        return motifService.addMotif(motif);
    }


    @GetMapping("/motifs/operation/{operationId}")
    public List<Motif> findMotifsByOperation(@PathVariable(name = "operationId") Long operationId){

        Operation operation=operationService.findOperationById(operationId);
        return motifService.findMotifByOperation(operation);
    }
}
