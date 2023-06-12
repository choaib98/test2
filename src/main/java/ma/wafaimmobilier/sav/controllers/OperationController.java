package ma.wafaimmobilier.sav.controllers;

import ma.wafaimmobilier.sav.entities.Operation;
import ma.wafaimmobilier.sav.exceptions.OperationNotFoundException;
import ma.wafaimmobilier.sav.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sav")
@CrossOrigin("*")
public class OperationController {
    @Autowired
    private OperationService operationService;


    @GetMapping("operations")
    public List<Operation> getAllOperations(){


        return operationService.findAllOperations();


    }


    @GetMapping("operation/{id}")
    public Operation getOperationById(@PathVariable(name="id") Long id){
        Operation operation=operationService.findOperationById(id);
        if(operation==null){
            throw new OperationNotFoundException(id);
        }
        return operationService.findOperationById(id);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("operations")
    public Operation addOperation(@RequestBody Operation operation){
        return operationService.addOperation(operation);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping(value = "/operation/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Operation> patchOperation(@PathVariable(name = "id") Long operationId,
                                                    @RequestBody Map<String, Object> mapOperation) {
        Operation operation=operationService.patchOperation(operationId,mapOperation);
        return ResponseEntity.ok(operation);
    }


}
