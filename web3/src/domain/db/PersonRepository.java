package domain.db;

import domain.model.User;

import java.util.List;

public interface PersonRepository {
    public User get(String id) throws DbException;
    public List<User> getAll() throws DbException;
    public void add(User user) throws DbException;
    public void update(User user) throws DbException;
    public void delete(String id) throws DbException;
}
