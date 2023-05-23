package com.vorona.CRUD.Service;

import com.vorona.CRUD.model.User;

import java.util.List;

public interface UserService {
    public List<User> getUser(/*int count*/);
    public void save (User user);
    public User show (long id);
    public void update(long id, User updareUser);
    public void delete(long id);
}
