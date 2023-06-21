package goit.jdbc.crud.project;

import goit.jdbc.crud.project.DAO.ClientService;
import goit.jdbc.crud.project.model.Client;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {
    private Connection connection = connection = ConectionProviderTest.getInstance().getConnection();
    private ClientService clientService;

    @BeforeAll
    static void makeMigration() {
        DatabaseInitServiceTest.initDb();
    }

    @BeforeEach
    void getClientService() {
        clientService = new ClientService(connection);
    }

    private List<Client> cresteListNewClients() {
        List<Client> clientsForAddingInBd = new ArrayList<>();
        Client clientTestOne = new Client();
        Client clientTestTwo = new Client();
        try {
            clientTestTwo.setName("Masha");
            clientTestOne.setName("Bob");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
        clientsForAddingInBd.add(clientTestOne);
        clientsForAddingInBd.add(clientTestTwo);
        return clientsForAddingInBd;
    }

    @Test
    void listAll_AddClient_GetRightCount() throws IllegalArgumentException {
        List<Client> clientsForAddingInBd = cresteListNewClients();
        List<Client> clientsCountStart = clientService.listAll();
        for (Client client : clientsForAddingInBd) {
            clientService.create(client.getName());
        }
        List<Client> clientsActual = clientService.listAll();
        assertEquals(clientsActual.size(), clientsCountStart.size() + clientsForAddingInBd.size());
    }

    @Test
    void deleteById_SetZeroArgument_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> clientService.deleteById(0));
    }

    @Test
    void deleteById_SetArgumentLessZero_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> clientService.deleteById(-3));
    }

    @Test
    void deleteById_SetIdDoesNotExist_ThrowException() {
        long maxId = clientService.getMaxId();
        assertThrows(IllegalArgumentException.class, () -> clientService.deleteById(maxId + 10));
    }

    @Test
    void deleteById_SetIdExist_PositiveResult() {
        clientService.create("Ganna");
        long idClientGanna = clientService.getMaxId();
        clientService.deleteById(idClientGanna);
        List<Client> clients = clientService.listAll();
        List<Long> idList = new ArrayList<>();
        for (Client client : clients) {
            idList.add(client.getId());
        }
        assertFalse(clients.contains(idClientGanna));
    }

    @Test
    void setName_SetIdDoesNotExistNameValid_ThrowException() {
        long maxId = clientService.getMaxId();
        assertThrows(IllegalArgumentException.class, () -> clientService.setName(maxId + 5, "Hanna"));
    }

    @Test
    void setName_SetIdLessZeroNameValid_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> clientService.setName(-5, "Hanna"));
    }

    @Test
    void setName_SetIdZeroNameValid_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> clientService.setName(0, "Hanna"));
    }

    @Test
    void setName_SetIdValidNameNull_ThrowException() {
        long maxId = clientService.getMaxId();
        assertThrows(IllegalArgumentException.class, () -> clientService.setName(maxId, null));
    }

    @Test
    void setName_SetIdValidNameMore1000Simbols_ThrowException() {
        StringBuilder testName = new StringBuilder();
        for (int i = 0; i < 1010; i++) {
            testName.append("a");
        }
        long maxId = clientService.getMaxId();
        assertThrows(IllegalArgumentException.class, () -> clientService.setName(maxId, testName.toString()));
    }

    @Test
    void setName_SetIdValidNamelessTwoSimbols_ThrowException() {
        StringBuilder testName = new StringBuilder();
        testName.append("a");
        long maxId = clientService.getMaxId();
        assertThrows(IllegalArgumentException.class, () -> clientService.setName(maxId, testName.toString()));
    }

    @Test
    void setName_SetIdNotValidNamelessTwoSimbols_ThrowException() {
        StringBuilder testName = new StringBuilder();
        testName.append("a");
        assertThrows(IllegalArgumentException.class, () -> clientService.setName(-1, testName.toString()));
    }

    @Test
    void setName_SetIdValidNameValid_PositiveResult() {
        long maxId = clientService.getMaxId();
        clientService.setName(maxId, "Nico");
        String expectedName = clientService.getById(maxId);
        assertEquals("Nico", expectedName);
    }

    @Test
    void getById_SetIdZero_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> clientService.getById(0));
    }

    @Test
    void getById_SetIdLessZero_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> clientService.getById(-1));
    }

    @Test
    void getById_SetIdNotExist_ThrowException() {
        long maxId = clientService.getMaxId();
        assertThrows(IllegalArgumentException.class, () -> clientService.getById(maxId + 3));
    }

    @Test
    void getById_SetIdValid_PositiveResult() {
        clientService.create("Georg");
        long maxId = clientService.getMaxId();
        String maneByMaxId = clientService.getById(maxId);
        assertEquals("Georg", maneByMaxId);
    }

    @Test
    void create_SetNameLessTwoSimbols_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> clientService.create("a"));
    }

    @Test
    void create_SetNameMore1000Simbols_ThrowException() {
        StringBuilder testName = new StringBuilder();
        testName.append("a");
        assertThrows(IllegalArgumentException.class, () -> clientService.create(testName.toString()));
    }

    @Test
    void create_SetNameNull_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> clientService.create(null));
    }

    @Test
    void create_SetNameValid_PositiveResult() {
        clientService.create("Julia");
        long maxId = clientService.getMaxId();
        String nameLastClient = clientService.getById(maxId);
        assertEquals(nameLastClient, "Julia");
    }

}