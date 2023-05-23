package com.vorona.CRUD.DAO;

import com.vorona.CRUD.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public List<User> getUser(/*int count*/) throws SQLException;
    public void save (User user);
    public User show (long id);
    public void update(long id, User updareUser);
    public void delete(long id);
}
