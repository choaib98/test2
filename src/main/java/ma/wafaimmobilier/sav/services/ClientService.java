package ma.wafaimmobilier.sav.services;

import ma.wafaimmobilier.sav.entities.Client;

import java.util.List;
import java.util.Map;

public interface ClientService {
    List<Client> findAllClients();

    Client findClientById(Long clientId);

    Client addClient(Client client);

    Client updateClient(Long clientId, Client client);

    void deleteClient(Long clientId);

    Client patchClient(Long clientId, Map<String,Object> mapClient);
}
