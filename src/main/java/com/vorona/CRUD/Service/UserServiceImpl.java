package com.vorona.CRUD.Service;

import com.vorona.CRUD.DAO.UserDaoImpl;
import com.vorona.CRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    final UserDaoImpl userDao;
    @Autowired
    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getUser(/*int count*/) {
        return userDao.getUser(/*count*/);
    }
    @Transactional
    public void save (User user) {
        userDao.save(user);
    }
    public User show (long id) {
        return userDao.show(id);
    }
    @Transactional
    public void update(long id, User updareUser) {
        userDao.update(id,updareUser);
    }
    @Transactional
    public void delete(long id) {
        userDao.delete(id);
    }
}
