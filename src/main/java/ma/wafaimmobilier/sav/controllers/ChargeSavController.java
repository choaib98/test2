package ma.wafaimmobilier.sav.controllers;

import ma.wafaimmobilier.sav.entities.ChargeSav;
import ma.wafaimmobilier.sav.entities.Client;
import ma.wafaimmobilier.sav.services.ChargeSavService;
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
public class ChargeSavController {
    @Autowired
    ChargeSavService chargeSavService;

    @GetMapping("chargesav")
    public List<ChargeSav> getAllChargeSavs(){
        return chargeSavService.findAllChargeSavs();
    }


    @GetMapping("chargesav/{id}")
    public ChargeSav getChargeSavById(@PathVariable(name="id") Long id){
        ChargeSav chargeSav=chargeSavService.findChargeSavById(id);
        /*if(operation==null){
            throw new OperationNotFoundException(id);
        }*/
        return chargeSavService.findChargeSavById(id);
    }


    @PostMapping("chargesav")
    public ChargeSav addChargeSav(@RequestBody ChargeSav chargeSav){
        return chargeSavService.addChargeSav(chargeSav);
    }


    @PatchMapping(value = "/chargesav/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChargeSav> patchChargeSav(@PathVariable(name = "id") Long chargeSavId,
                                                  @RequestBody Map<String, Object> mapChargeSav) {
        ChargeSav chargeSav=chargeSavService.patchChargeSav(chargeSavId,mapChargeSav);
        return ResponseEntity.ok(chargeSav);
    }
}
