package pl.lodz.p.service;

import pl.lodz.p.model.user.Client;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IClientService {
    Client createClient(Client client);

    List<Client> getAllClients();

    Client getClient(UUID uuid);

    void updateClient(UUID uuid, Map<String, Object> fieldsToUpdate);

    void activateClient(UUID uuid);

    void deactivateClient(UUID uuid);

    //void deleteClient(UUID uuid);

    Client getClientByUsername(String username);

    List<Client> getClientsByUsername(String username);
}
