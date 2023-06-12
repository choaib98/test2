package ma.wafaimmobilier.sav.controllers;

import ma.wafaimmobilier.sav.entities.Client;
import ma.wafaimmobilier.sav.entities.DemandeDoc;
import ma.wafaimmobilier.sav.entities.Operation;
import ma.wafaimmobilier.sav.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sav")
@CrossOrigin("*")
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping("clients")
    public List<Client> getAllClients(){
        return clientService.findAllClients();
    }


    @GetMapping("clients/{id}")
    public Client getClientById(@PathVariable(name="id") Long id){
        Client client=clientService.findClientById(id);
        /*if(operation==null){
            throw new OperationNotFoundException(id);
        }*/
        return clientService.findClientById(id);
    }


    @PostMapping("clients")
    public Client addClient(@RequestBody Client client){
        return clientService.addClient(client);
    }


    @PatchMapping(value = "/clients/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> patchDemandeDoc(@PathVariable(name = "id") Long clientId,
                                                      @RequestBody Map<String, Object> mapClient) {
        Client client=clientService.patchClient(clientId,mapClient);
        return ResponseEntity.ok(client);
    }
}
