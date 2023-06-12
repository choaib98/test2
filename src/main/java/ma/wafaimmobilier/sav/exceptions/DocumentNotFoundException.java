package ma.wafaimmobilier.sav.exceptions;

public class DocumentNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 5675770841307011222L;

    public DocumentNotFoundException(Long id) {
        super("Document non trouvé avec l'identifiant : " + id);
    }

    public DocumentNotFoundException(String property, String value) {
        super("Document non trouvé avec la "+property+" : " + value);
    }
}
