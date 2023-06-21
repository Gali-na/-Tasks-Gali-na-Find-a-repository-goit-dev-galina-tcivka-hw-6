package goit.jdbc.crud.project.DAO;
import goit.jdbc.crud.project.model.Client;
import goit.jdbc.crud.project.srorage.ConectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClientService implements ClientDAO {
    private  final Connection connection;

    public ClientService(Connection connection) {
        this.connection = connection;
    }

    public List<Client> listAll() throws IllegalArgumentException {
        List<Client> clients = new ArrayList<>();
        String sqlGetALLClient = "SELECT * FROM client";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlGetALLClient);
            ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong(1));
                client.setName(resultSet.getString(2));
                clients.add(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }

    public void deleteById(long id) throws IllegalArgumentException {
        idVslidation(id);
        String sqlDeleteById = "DELETE FROM client WHERE id=(?)";
        try (PreparedStatement preparedStatementDeleteById = connection.prepareStatement(sqlDeleteById)) {
            preparedStatementDeleteById.setLong(1, id);
            preparedStatementDeleteById.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setName(long id, String name) throws IllegalArgumentException {
        nameValidation(name);
        idVslidation(id);
        String sqlNewNameForClient = " UPDATE client SET name =(?) WHERE id=(?)";
        try (PreparedStatement preparedStatementNewNAme = connection.prepareStatement(sqlNewNameForClient)) {
            preparedStatementNewNAme.setString(1, name);
            preparedStatementNewNAme.setLong(2, id);
            preparedStatementNewNAme.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getById(long id) throws IllegalArgumentException {
        String nameClient = "According to this Id client not found";
        idVslidation(id);
        String sqlClientById = "SELECT name FROM client WHERE id=(?)";
        try (PreparedStatement preparedStatementGetMaxId = connection.prepareStatement(sqlClientById)) {
            preparedStatementGetMaxId.setLong(1, id);

            try (ResultSet resultSet = preparedStatementGetMaxId.executeQuery()) {
                if (resultSet.first()) {
                    nameClient = resultSet.getString("name");
                } else {
                    return nameClient;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nameClient;
    }

    public long create(String name) throws IllegalArgumentException {
        long idNewClient = 0;
        String addingСlientByName = "INSERT INTO client (name) VALUES (?) ";
        nameValidation(name);
        try (PreparedStatement preparedStatementAddClient = connection.prepareStatement(addingСlientByName);) {
            preparedStatementAddClient.setString(1, name);
            preparedStatementAddClient.executeUpdate();
            idNewClient = getMaxId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idNewClient;
    }

    private void nameValidation(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Argument cannot be null");

        }
        if (name.length() > 1000 || name.length() < 2) {
            throw new IllegalArgumentException("The name is incorrect, the number of characters in" +
                    " the name must not be less than 2 or more than 1000");
        }
    }

    private void idVslidation(long id) throws IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Iв cannot be equal to or less than 0");
        }
        String sqlClientById = "SELECT name FROM client WHERE id=(?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlClientById)) {
             preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {

            } else {
                throw new IllegalArgumentException("The specified id does not exist in the database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long getMaxId() {
        long idNewClient = 0;
        String sqlMaxId = "SELECT MAX(id) FROM client";
        try (PreparedStatement preparedStatementGetMaxId = connection.prepareStatement(sqlMaxId);
             ResultSet resultSet = preparedStatementGetMaxId.executeQuery()) {
            if (resultSet.first()) {
                idNewClient = resultSet.getLong(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return idNewClient;
    }
}


