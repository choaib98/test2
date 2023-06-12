package ma.wafaimmobilier.sav.services;

import ma.wafaimmobilier.sav.entities.Document;
import ma.wafaimmobilier.sav.entities.Operation;
import ma.wafaimmobilier.sav.repositories.DocumentRepository;
import ma.wafaimmobilier.sav.utils.PatchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService{
    @Autowired
    private DocumentService documentService;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private PatchHelper patchHelper;
    @Autowired
    private OperationService operationService;
    @Override
    public List<Document> findAllDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public Document findDocumentById(Long documentId) {
        return documentRepository.findById(documentId).orElseThrow();
    }

    @Override
    public Document addDocument(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public Document updateDocument(Long documentId, Document document) {
        documentService.findDocumentById(documentId);
      // document.setDocumentId(documentId);
        return documentRepository.save(document);
    }

    @Override
    public void deleteDocument(Long documentId) {
        documentRepository.deleteById(documentId);

    }

    @Override
    public Document patchDocument(Long documentId, Map<String, Object> mapDocument) {
        Document document=documentService.findDocumentById(documentId);
        Document patchedDocument=patchHelper.mergePatch(mapDocument,document, Document.class);
        return documentService.updateDocument(documentId,patchedDocument);
    }

    @Override
    public List<Document> findDocumentsByOperation(Operation operation) {
        return documentRepository.findDocumentByOperation(operation);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/operation/{operationId}")
    public List<Document> findDocumentsByOperation(@PathVariable(name = "operationId") Long operationId){
        Operation operation=operationService.findOperationById(operationId);
        return documentService.findDocumentsByOperation(operation);
    }
}
