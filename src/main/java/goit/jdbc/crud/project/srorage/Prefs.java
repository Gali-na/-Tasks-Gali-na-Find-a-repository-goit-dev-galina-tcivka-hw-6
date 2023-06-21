package goit.jdbc.crud.project.srorage;

import java.util.ResourceBundle;

public class Prefs {
    private static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("database");
    }

    public static final String DB_JDBS_CONECTION_URL = getResourceBundle().getString("dbURL");

}
