package ma.wafaimmobilier.sav.services;

import ma.wafaimmobilier.sav.entities.Operation;

import java.util.List;
import java.util.Map;

public interface OperationService {
    List<Operation> findAllOperations();

    Operation findOperationById(Long operationId);

    Operation addOperation(Operation operation);

    Operation updateOperation(Long operationId, Operation operation);

    void deleteOperation(Long operationId);

    Operation patchOperation(Long operationId, Map<String,Object> mapOperation);
}
