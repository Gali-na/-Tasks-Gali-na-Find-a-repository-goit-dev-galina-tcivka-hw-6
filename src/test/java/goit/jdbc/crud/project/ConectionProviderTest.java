package goit.jdbc.crud.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConectionProviderTest {
        private String DB_JDBS_CONECTION_URL_TEST="jdbc:h2:./db_junit_test2";
        private static ConectionProviderTest instance;
        private Connection connection;
        private ConectionProviderTest() {
            try {
                connection = DriverManager.getConnection(DB_JDBS_CONECTION_URL_TEST);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        public static ConectionProviderTest getInstance() {
            if (instance == null) {
                instance = new ConectionProviderTest();
            }
            return instance;
        }

        public  Connection getConnection(){

            return connection;
        }

}
