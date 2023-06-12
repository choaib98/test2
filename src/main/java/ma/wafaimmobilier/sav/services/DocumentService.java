package ma.wafaimmobilier.sav.services;


import ma.wafaimmobilier.sav.entities.Document;
import ma.wafaimmobilier.sav.entities.Operation;

import java.util.List;
import java.util.Map;

public interface DocumentService {
    List<Document> findAllDocuments();

    Document findDocumentById(Long documentId);

    Document addDocument(Document document);

    Document updateDocument(Long documentId, Document document);

    void deleteDocument(Long documentId);

    Document patchDocument(Long documentId, Map<String,Object> mapDocument);
    List<Document>findDocumentsByOperation(Operation operation);
}
