package pl.lodz.p;

import com.mongodb.client.model.IndexOptions;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import pl.lodz.p.manager.ClientManager;
import pl.lodz.p.manager.RentManager;
import pl.lodz.p.manager.VMachineManager;
import pl.lodz.p.model.*;
import pl.lodz.p.model.user.Premium;
import pl.lodz.p.model.user.Client;
import pl.lodz.p.model.user.Standard;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApplicationScoped
public class DataInitializer {
    private ClientManager clientMan = ClientManager.getInstance();
    private RentManager rentMan = RentManager.getInstance();
    private VMachineManager vmMan = VMachineManager.getInstance();
    List<Client> clients = new ArrayList<>();
    List<Rent> rents = new ArrayList<>();
    List<VMachine> vms = new ArrayList<>();
    @PostConstruct
    public void init(){
        clients = new ArrayList<>();
        rents = new ArrayList<>();
        vms = new ArrayList<>();
        initClient();
        initVM();
        initRent();
    }

    public void dropAndCreateClient(){
        clientMan.getClientsRepository().getDatabase().getCollection("clients").drop();
        clientMan.getClientsRepository().getDatabase().createCollection("clients");
        clientMan.getClientsRepository().getDatabase().getCollection("clients").createIndex(
                new Document("username", 1),
                new IndexOptions().unique(true)
        );
    }

    public void dropAndCreateVMachine(){
        vmMan.getVMachinesRepository().getDatabase().getCollection("vMachines").drop();
        vmMan.getVMachinesRepository().getDatabase().createCollection("vMachines");
    }

    public void dropAndCreateRent(){
        rentMan.getRentRepository().getDatabase().getCollection("rents").drop();
        rentMan.getRentRepository().getDatabase().getCollection("clients").drop();
        rentMan.getRentRepository().getDatabase().getCollection("vMachines").drop();
        rentMan.getRentRepository().getDatabase().createCollection("vMachines");
        rentMan.getRentRepository().getDatabase().createCollection("clients");
        rentMan.getRentRepository().getDatabase().createCollection("rents");
    }

    public void initClient(){
        clients.add(new Client("Bart", "Fox", "Idontexist", "BFox@tul.com", new Premium()));
        clients.add(new Client("Michael", "Corrugated", "DON_IAS", "MCorrugated@ias.pas.p.lodz.pl", new Premium()));
        clients.add(new Client("Matthew", "Tar", "MTar", "MTar@TarVSCorrugated.com", new Premium()));
        clients.add(new Client("Martin", "Bricky", "Brickman", "IntelEnjoyer@whatisonpage4035.com", new Standard()));
        clients.add(new Client("Juan", "Escobar", "JEscobar", "JEscobar@colombianSnow.com", new Standard()));
        clientMan.registerExistingClient(clients.get(0));
        clientMan.registerExistingClient(clients.get(1));
        clientMan.registerExistingClient(clients.get(2));
        clientMan.registerExistingClient(clients.get(3));
        clientMan.registerExistingClient(clients.get(4));
    }


    public void initVM(){
        vms.add(new AppleArch(4, "4GB"));
        vms.add(new AppleArch(24, "128GB"));
        vms.add(new x86(8, "8GB", "AMD"));
        vms.add(new x86(16, "32GB", "Intel"));
        vms.add(new x86(128, "256GB", "Other"));
        vms.add(new x86(128, "256GB", "Other"));
        vmMan.registerExistingVMachine(vms.get(0));
        vmMan.registerExistingVMachine(vms.get(1));
        vmMan.registerExistingVMachine(vms.get(2));
        vmMan.registerExistingVMachine(vms.get(3));
        vmMan.registerExistingVMachine(vms.get(4));
        vmMan.registerExistingVMachine(vms.get(5));
    }

    public void initRent(){
        rents.add(new Rent(clients.get(0), vms.get(0), LocalDateTime.of(2024,11,21,21,37)));
        rents.add(new Rent(clients.get(0), vms.get(2), LocalDateTime.of(2024,10,26,21,37)));
        rents.add(new Rent(clients.get(3), vms.get(3), LocalDateTime.of(2023,10,26,21,37)));
        rents.add(new Rent(clients.get(4), vms.get(1), LocalDateTime.of(2023,11,11,11,11)));
        rents.add(new Rent(clients.get(1), vms.get(4), LocalDateTime.of(2011,11,11,11,11)));
        rentMan.registerExistingRent(rents.get(0));
        rentMan.registerExistingRent(rents.get(1));
        rentMan.registerExistingRent(rents.get(2));
        rentMan.registerExistingRent(rents.get(3));
        rentMan.registerExistingRent(rents.get(4));
    }
}