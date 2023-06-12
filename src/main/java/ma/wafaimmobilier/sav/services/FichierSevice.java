package ma.wafaimmobilier.sav.services;

import org.springframework.core.io.Resource;

public interface FichierSevice {

	Resource telecharger(String filename);

}
