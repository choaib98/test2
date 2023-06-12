package ma.wafaimmobilier.sav.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import ma.wafaimmobilier.sav.services.FichierSevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/sav/file")
@CrossOrigin("*")
public class FichierJointController {
	Logger logger = LoggerFactory.getLogger(FichierJointController.class);
	@Value("${file.ipload.path}")
	private String filesPath;
	@Autowired
	private FichierSevice fichierService;
	

	@PostMapping(value = "/api/sav/fichierJoint/upload")
	public void uploaddFile(MultipartFile file) {
		try {
			Files.write(Paths.get(filesPath + file.getOriginalFilename()), file.getBytes());
		} catch (IOException e) {
			logger.error("probleme upload du fichier {} ",e.getMessage());
		}

	}

	@GetMapping(value = "fichierJoint/downoald/{fileName:.+}")
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {
		Resource file = fichierService.telecharger(fileName);
		Path path = file.getFile().toPath();

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping(value = "fichierJoint/removeFile")
	public void supprimerFichier(@RequestParam String fileName) {
		try {
			Files.delete(Paths.get(filesPath + fileName));
		} catch (IOException e) {
			logger.error("problème supression du fichier {} ",e.getMessage());
		}

	}

}
