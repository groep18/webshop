package domain.db;
import domain.model.Person;
import java.util.List;

public interface PersonRepository {
    void add(Person person);

    List<Person> getAll();
}
