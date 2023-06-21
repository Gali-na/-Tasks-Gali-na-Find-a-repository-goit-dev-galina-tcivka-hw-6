package goit.jdbc.crud.project;

import org.flywaydb.core.Flyway;

public class DatabaseInitServiceTest {
     public static void  initDb() {
          String DB_JDBS_CONECTION_URL_TEST="jdbc:h2:./db_junit_test2";
          Flyway flywayProject = Flyway.configure().dataSource(DB_JDBS_CONECTION_URL_TEST,null,null).load();
          flywayProject.migrate();
     }


}
