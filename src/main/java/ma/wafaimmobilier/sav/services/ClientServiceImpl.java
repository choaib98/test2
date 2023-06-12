package ma.wafaimmobilier.sav.services;

import ma.wafaimmobilier.sav.entities.Client;
import ma.wafaimmobilier.sav.entities.Operation;
import ma.wafaimmobilier.sav.repositories.ClientRepository;
import ma.wafaimmobilier.sav.utils.PatchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ClientServiceImpl implements ClientService{
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private PatchHelper patchHelper;

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client findClientById(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow();
    }

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Long clientId, Client client) {
       /* clientService.findClientById(clientId);
        client.setClientId(clientId);
        return clientRepository.save(client);*/
        return null;
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public Client patchClient(Long clientId, Map<String, Object> mapClient) {

        Client client=clientService.findClientById(clientId);
        Client patchedClient=patchHelper.mergePatch(mapClient,client, Client.class);
        return clientService.updateClient(clientId,patchedClient);
    }
}
