package domain.model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class User {

    private String id;
    private Person person;
    private String password;

    public User(String id, String password,  String email, String firstName, String lastName) throws DomainException {
        this(id, password, new Person(email, firstName, lastName));
    }

    public User(String id, String password,  Person person) throws DomainException {
        setId(id);
        setPassword(password);
        setPerson(person);
    }

    public User() {
        this.person = new Person();
    }

    public void setPassword(String password) throws DomainException {
        if (password == null || password.isEmpty()) {
            throw new DomainException("No password given.");
        }
        this.password = password.trim();
    }

    public void setAndHashPassword(String password)
            throws NoSuchAlgorithmException, UnsupportedEncodingException, DomainException {
        if (password == null || password.isEmpty()) {
            throw new DomainException("No password given.");
        }

        String hashedPassword = hashPassword(password);
        setPassword(hashedPassword);
    }



    private String hashPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password.getBytes("UTF-8"));
        byte[] digest = crypt.digest();
        String hashedPassword = new BigInteger(1, digest).toString(16);
        return hashedPassword;
    }


    public boolean isCorrectPassword(String password)
            throws NoSuchAlgorithmException, UnsupportedEncodingException, DomainException {
        if (password == null || password.isEmpty()) {
            throw new DomainException("No password given.");
        }

        String hashedPassword = hashPassword(password);
        return getPassword().equals(hashedPassword);
    }

    public String getPassword() {
        return password;
    }


    //PERSON-SPECIFIC GETTERS & SETTERS
    public String getEmail() {
        return this.person.getEmail();
    }

    public String getFirstName() {
        return this.person.getFirstName();
    }

    public String getLastName() {
        return this.person.getLastName();
    }

    public void setEmail(String email) throws DomainException {
        this.person.setEmail(email);
    }

    public void setFirstName(String firstName) throws DomainException {
        this.person.setFirstName(firstName);
    }

    public void setLastName(String lastName) throws DomainException {
        this.person.setLastName(lastName);
    }

    //OTHER GETTERS  & SETTERS

    public String getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }


    public void setId(String id) throws DomainException {
        if(id == null || id.isEmpty()){
            throw new DomainException("No user ID given.");
        }
        this.id = id;
    }

    public void setPerson(Person person) throws DomainException {
        if(person == null) {
            throw new DomainException("No person given.");
        }
        this.person = person;
    }


}
