package domain.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Person {

    private String email;
    private String firstName;
    private String lastName;

    public Person(String email, String firstName, String lastName) throws DomainException {
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public Person() {
    }

    //GETTERS & SETTERS

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) throws DomainException {
        if(email == null || email.isEmpty()){
            throw new DomainException("No email given.");
        }
        String USERID_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new DomainException("Email not valid.");
        }
        this.email = email;
    }

    public void setFirstName(String firstName) throws DomainException {
        if(firstName == null || firstName.isEmpty()){
            throw new DomainException("No first name given.");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) throws DomainException {
        if(lastName == null || lastName.isEmpty()){
            throw new DomainException("No last name given.");
        }
        this.lastName = lastName;
    }

}
