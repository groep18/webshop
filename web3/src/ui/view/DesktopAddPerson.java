package ui.view;

import domain.db.DbException;
import domain.db.PersonRepository;
import domain.db.PersonDBSQL;
import domain.model.Person;

import javax.swing.*;
import java.sql.*;
import java.util.Properties;

public class DesktopAddPerson {
    /*public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        String url = "jdbc:postgresql://databanken.ucll.be:51819/2TX38?currentSchema=r0716543_test";
        properties.setProperty("user", "r0716543");
        properties.setProperty("password", "Qé)²JnDN6G²C)3c-");
        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        properties.setProperty("sslmode","prefer");

        Connection connection = DriverManager.getConnection(url,properties);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery( "SELECT * FROM person" );

        String userid = JOptionPane.showInputDialog("Input user ID");
        String email = JOptionPane.showInputDialog("Input email");
        String firstName = JOptionPane.showInputDialog("Input first name");
        String lastName = JOptionPane.showInputDialog("Input last name");
        String password = JOptionPane.showInputDialog("Input password");

        Person person = new Person(userid, email, firstName, lastName, password);
        PersonDBSQL personDB = new PersonDBSQL();
        personDB.add(person);

        statement.close();
        connection.close();
    }*/
}