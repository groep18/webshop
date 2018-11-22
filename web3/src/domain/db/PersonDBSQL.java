package domain.db;

import domain.model.Person;
import domain.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersonDBSQL implements PersonRepository {

    private Properties properties = new Properties();
    private String url = "jdbc:postgresql://databanken.ucll.be:51819/2TX38";
    private String currentSchama;
    public PersonDBSQL(Properties properties) {
        try {
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.url = properties.getProperty("url");
            this.currentSchama = properties.getProperty("currentSchema");
        } catch (ClassNotFoundException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void add(Person person) {
        if (person == null) {
            throw new DbException("Nothing to add.");
        }
        String sql = "INSERT INTO person (userid, email, firstname, lastname, pass) VALUES ('"
                + person.getUserid() + "', '" + person.getEmail() + "', '" + person.getFirstName() + "', '"
                + person.getLastName() + "', '" + person.getPassword() + "')";
        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public List<Person> getAll() {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery("SELECT * FROM person");
            while (result.next()) {
                String userid = result.getString("userid");
                String email = result.getString("email");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                String password = result.getString("pass");
                Person person = new Person(userid, email, firstName, lastName, password);
                persons.add(person);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return persons;
    }
}
