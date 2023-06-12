package ma.wafaimmobilier.sav.services;

import ma.wafaimmobilier.sav.entities.DemandeDoc;
import ma.wafaimmobilier.sav.entities.DemandeSav;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/sav/upload/")
@CrossOrigin("*")
public class MultipleFilesUploadController {
@Value("${file.ipload.path}")

private String pathUpload;

    @Autowired
    private DemandeDocService demandeDocService;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private DemandeSavService demandeSavService;
    @PostMapping(value="documents/{idDossier}/{idOperation}")
    public ResponseEntity<Void> uploadPolicyDocument(@RequestParam("document") List<MultipartFile> multipartFile,@PathVariable(name = "idDossier") Long idDossier,@PathVariable(name = "idOperation") Long idOperation)
    {

        try {
          DemandeSav demandeSav= demandeSavService.addDemandeSav(idDossier,idOperation);
            List<DemandeDocDto> demandeDocDtos=new ArrayList<>();
            for(MultipartFile mf: multipartFile)
            {

                byte[] bytes = mf.getBytes();
                Path path = Paths.get(pathUpload + mf.getOriginalFilename());
                System.out.println(mf.getName());

                demandeDocDtos.add( new DemandeDocDto(mf.getOriginalFilename().split("\\.")[0],pathUpload + mf.getOriginalFilename(), mf.getOriginalFilename(),demandeSav.getDemandeSavId()));

                Files.write(path, bytes);



            }

            demandeDocService.addDemandeDocService(demandeDocDtos);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().build();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/download/{demandeDocId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long demandeDocId) throws IOException {
       /* DemandeSav demandeSav=demandeSavService.findDemandeSavById(demandeSavId);
        List<DemandeDocDto> demandeDocDtos=demandeDocService.findDemandeDocDtoByDemandeSav(demandeSav);*/
        byte[] fileContent = Files.readAllBytes(new File(demandeDocService.findDemandeDocServiceById(demandeDocId).getPath()).toPath());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData((demandeDocService.findDemandeDocServiceById(demandeDocId).getLibelle()), demandeDocService.findDemandeDocServiceById(demandeDocId).getLibelle());
        headers.setContentLength(fileContent.length);

        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
    }


}
