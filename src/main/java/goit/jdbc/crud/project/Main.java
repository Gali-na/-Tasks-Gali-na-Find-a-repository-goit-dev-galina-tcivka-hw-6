package goit.jdbc.crud.project;

import goit.jdbc.crud.project.DAO.ClientDAO;
import goit.jdbc.crud.project.DAO.ClientService;
import goit.jdbc.crud.project.model.Client;
import goit.jdbc.crud.project.srorage.ConectionProvider;
import goit.jdbc.crud.project.srorage.DatabaseInitService;
import goit.jdbc.crud.project.srorage.Prefs;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        ClientService clientService;
        try (Connection connection = ConectionProvider.getInstance().getConnection()) {
            clientService = new ClientService(connection);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
}
