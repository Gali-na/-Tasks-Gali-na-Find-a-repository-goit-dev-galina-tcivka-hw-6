package goit.jdbc.crud.project.model;


/*CREATE TABLE IF NOT EXISTS client (
id IDENTITY PRIMARY KEY,
name VARCHAR(1000) NOT NULL CHECK (LENGTH(name) >= 2 AND LENGTH(name) <= 1000)
);*/


import lombok.*;

@Getter
@Setter
@ToString
public class Client {
    private long id;
    private String name;

    public void setName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        if (name.length() > 1000 || name.length() < 2) {
            throw new IllegalArgumentException("The name is incorrect, the number of characters in" +
                    " the name must not be less than 2 or more than 1000");
        }
        this.name = name;
    }

}
