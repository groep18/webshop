package domain.db;

import domain.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDbInMemory {
    private Map<String, Person> persons = new HashMap<>();

    public PersonDbInMemory () {
        Person administrator = new Person("001", "admin@ucll.be", "t", "Ad", "Ministrator");
        Person admin2 = new Person("002", "admin2@ucll.be", "tish", "Ola", "Bola");
        add(administrator);
        add(admin2);
    }

    public Person get(String personId){
        if(personId == null){
            throw new DbException("No id given");
        }
        return persons.get(personId);
    }

    public List<Person> getAll(){
        return new ArrayList<Person>(persons.values());
    }

    public void add(Person person){
        if(person == null){
            throw new DbException("No person given");
        }
        if (persons.containsKey(person.getUserid())) {
            throw new DbException("User already exists");
        }
        persons.put(person.getUserid(), person);
    }

    public void update(Person person){
        if(person == null){
            throw new DbException("No person given");
        }
        if(!persons.containsKey(person.getUserid())){
            throw new DbException("No person found");
        }
        persons.put(person.getUserid(), person);
    }

    public void delete(String personId){
        if(personId == null){
            throw new DbException("No id given");
        }
        persons.remove(personId);
    }

    public int getNumberOfPersons() {
        return persons.size();
    }
}
