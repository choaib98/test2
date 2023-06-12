package ma.wafaimmobilier.sav.exceptions;

public class DossierNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 5675770841307011222L;

    public DossierNotFoundException(Long id){
        super("Dossier non trouvée avec l'identifiant : " + id);
    }

    public DossierNotFoundException(String property, String value){
        super("Dossier non trouvée avec la " + property + " : " + value);
    }
}
