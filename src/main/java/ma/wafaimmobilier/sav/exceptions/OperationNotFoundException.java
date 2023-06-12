package ma.wafaimmobilier.sav.exceptions;

public class OperationNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 5675770841307011222L;
    public OperationNotFoundException(Long id){
        super("Opération non trouvée avec l'identifiant : " + id);

    }

    public OperationNotFoundException(String property, String value) {
        super("Opération non trouvée avec la " + property + " : " + value);
    }

}
