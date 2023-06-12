package ma.wafaimmobilier.sav.services;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;


@Service
public class FichierServiceImpl implements FichierSevice {

    @Value("${file.ipload.path}")
    private String filesPath;

    @Override
    public Resource telecharger(String filename) {
        try {
            Path file = Paths.get(filesPath)
                             .resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

   
}
