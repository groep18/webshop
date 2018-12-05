package domain.db;

import domain.model.DomainException;
import domain.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersonDBSQL implements PersonRepository {
    private PreparedStatement statement;
    private Connection connection;
    private Properties properties;
    private String url;
    private String schema;

    public PersonDBSQL(Properties properties) throws DbException {
        try {
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.url = properties.getProperty("url");
            this.schema = properties.getProperty("currentSchema");
        } catch (ClassNotFoundException e) {
            throw new DbException(e.getMessage(), e);
        }
    }


    @Override
    public User get(String id) throws DbException, DomainException {
        if(id == null || id.isEmpty()) {
            throw new DbException("No user ID given.");
        }


        try {
            String sql = "SELECT * FROM " + schema + ".person "
                    + "WHERE LOWER(userID) = LOWER(?)";

            statement = connection.prepareStatement(sql);
            statement.setString(1, id);

            ResultSet result = statement.executeQuery();

            if(!result.isBeforeFirst()) {
                throw new DbException("No user with given ID found.");
            }

            result.next();

            String email = result.getString("email");
            String password = result.getString("password");
            String firstName = result.getString("firstName");
            String lastName = result.getString("lastName");

            User u = new User(id, password,  email, firstName, lastName);

            return u;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<User> getAll() throws DbException, DomainException {
        List users = new ArrayList<User>();
        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement();){
            ResultSet result = statement.executeQuery("SELECT * FROM " + schema + ".person ORDER BY userid ASC");

            while(result.next()) {
                String id = result.getString("userid");
                String email = result.getString("email");
                String password = result.getString("pass");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");

                User u = new User(id, password, email, firstName, lastName);
                users.add(u);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return users;
    }



    @Override
    public void add(User user) throws DbException {
        if(user == null) {
            throw new DbException("No user to add.");
        }

        try {
            String sql = "SELECT * FROM " + schema + ".person "
                    + "WHERE userID = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getId());

            ResultSet result = statement.executeQuery();

            if(result.isBeforeFirst()) {
                throw new DbException("User already exists");
            }

            sql = "INSERT INTO " + schema + "person (userID, email, password, firstName, lastName)"
                    + "VALUES(?,?,?,?,?,?)";

            statement = connection.prepareStatement(sql);

            statement.setString(1, user.getId());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());

            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(User user) throws DbException {
        if(user == null) {
            throw new DbException("No user to update");
        }

        try {

            String sql = "SELECT * FROM " + schema + ".person "
                    + "WHERE userID = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getId());

            ResultSet result = statement.executeQuery();

            if(!result.isBeforeFirst()) {
                throw new DbException("No user found with given ID.");
            }

            sql = "UPDATE " + schema + ".person "
                    + "SET email = ?, password = ?, firstName = ?, lastName = ? "
                    + "WHERE userID = ?";

            statement = connection.prepareStatement(sql);

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void delete(String id) throws DbException {
        if(id == null || id.isEmpty()) {
            throw new DbException("No user ID given.");
        }


        try {
            String sql = "SELECT * FROM " + schema + ".person "
                    + "WHERE userID = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, id);

            ResultSet result = statement.executeQuery();

            if(!result.isBeforeFirst()) {
                throw new DbException("No user with given ID found.");
            }

            sql = "DELETE FROM " + schema + ".person "
                    + "WHERE userID = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
