package goit.jdbc.crud.project.srorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static goit.jdbc.crud.project.srorage.Prefs.DB_JDBS_CONECTION_URL;


public class ConectionProvider {
    private static ConectionProvider instance;
    private Connection connection;
    private ConectionProvider() {
        try {
            connection = DriverManager.getConnection(DB_JDBS_CONECTION_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static ConectionProvider getInstance() {
        if (instance == null) {
            instance = new ConectionProvider();
        }
        return instance;
    }

    public  Connection getConnection(){

        return connection;
    }
}
