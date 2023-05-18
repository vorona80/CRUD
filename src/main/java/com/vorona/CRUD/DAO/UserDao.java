package com.vorona.CRUD.DAO;

import com.vorona.CRUD.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public List<User> getUser(/*int count*/) throws SQLException;
    public void save (User user);
    public User show (int id);
    public void update(int id, User updareUser);
    public void delete(int id);
}
