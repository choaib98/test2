package ma.wafaimmobilier.sav.controllers;

import ma.wafaimmobilier.sav.entities.Client;
import ma.wafaimmobilier.sav.entities.DemandeDoc;
import ma.wafaimmobilier.sav.entities.DemandeSav;
import ma.wafaimmobilier.sav.services.ClientService;
import ma.wafaimmobilier.sav.services.DemandeDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sav")
@CrossOrigin("*")
public class DemandeDocController {
    @Autowired
    DemandeDocService demandeDocService;

    @GetMapping("demandedocs")
    public List<DemandeDoc> getAllDemandeDocs(){
        return demandeDocService.findAllDemandeDocService();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("demandedocs/{id}")
    public DemandeDoc getDemandeDocById(@PathVariable(name="id") Long id){
        DemandeDoc demandeDoc=demandeDocService.findDemandeDocServiceById(id);
        /*if(operation==null){
            throw new OperationNotFoundException(id);
        }*/
        return demandeDocService.findDemandeDocServiceById(id);
    }




    @PatchMapping(value = "/demandedocs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DemandeDoc> patchDemandeDoc(@PathVariable(name = "id") Long demandeDocId,
                                                      @RequestBody Map<String, Object> mapDemandeDoc) {
        DemandeDoc demandeDoc=demandeDocService.patchDemandeDocService(demandeDocId,mapDemandeDoc);
        return ResponseEntity.ok(demandeDoc);
    }



   /* @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/upload/{operationId}/{documentId}")
    public ResponseEntity<String> uploadDocument( @PathVariable(name = "operationId")Long operationId, @PathVariable(name = "documentId")Long  documentId,@RequestParam("file") MultipartFile... file){
        try {
            demandeDocService.storeDocument(operationId,documentId,file);
            return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Could not store file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
