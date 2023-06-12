package ma.wafaimmobilier.sav.services;

import ma.wafaimmobilier.sav.entities.Operation;
import ma.wafaimmobilier.sav.repositories.OperationRepository;
import ma.wafaimmobilier.sav.utils.PatchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OperationServiceImpl implements OperationService{
    @Autowired
    private OperationService operationService;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private PatchHelper patchHelper;
    @Override
    public List<Operation> findAllOperations() {
        return operationRepository.findAll();
    }

    @Override
    public Operation findOperationById(Long operationId) {
        return operationRepository.findById(operationId).orElseThrow();
    }

    @Override
    public Operation addOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    @Override
    public Operation updateOperation(Long operationId, Operation operation) {
        operationService.findOperationById(operationId);
        operation.setOperationId(operationId);
        return operationRepository.save(operation);
    }

    @Override
    public void deleteOperation(Long operationId) {
        operationRepository.deleteById(operationId);
    }

    @Override
    public Operation patchOperation(Long operationId, Map<String, Object> mapOperation) {
        Operation operation=operationService.findOperationById(operationId);
        Operation patchedOperation=patchHelper.mergePatch(mapOperation,operation, Operation.class);
        return operationService.updateOperation(operationId,patchedOperation);
    }
}
