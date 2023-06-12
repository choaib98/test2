package ma.wafaimmobilier.sav.controllers;

import ma.wafaimmobilier.sav.entities.Document;
import ma.wafaimmobilier.sav.entities.Operation;
import ma.wafaimmobilier.sav.services.DocumentService;
import ma.wafaimmobilier.sav.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/sav/documents")
@CrossOrigin("*")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private OperationService operationService;


    @GetMapping("/operation/{operationId}")
    public List<Document> findDocumentsByOperation(@PathVariable(name = "operationId") Long operationId){
        Operation operation=operationService.findOperationById(operationId);
        return documentService.findDocumentsByOperation(operation);
    }

    @GetMapping("")
    public List<Document> findAllDocuments(){

        return documentService.findAllDocuments();
    }



    @PostMapping("")
    public Document addDocument(@RequestBody Document document){
        return documentService.addDocument(document);
    }


   /* @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/upload/{operationId}/{documentId}")
    public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file, @PathVariable(name = "operationId")Long operationId, @PathVariable(name = "documentId")Long  documentId){
        try {
            documentService.storeDocument(file,operationId,documentId);
            return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Could not store file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @GetMapping("/{id}")
    public Document getDocumentById(Long id){
        return documentService.findDocumentById(id);
    }


    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Document> patchDocument(@PathVariable(name = "id") Long documentId,
                                                  @RequestBody Map<String, Object> mapDocument) {
        Document document=documentService.patchDocument(documentId,mapDocument);
        return ResponseEntity.ok(document);
    }

}
