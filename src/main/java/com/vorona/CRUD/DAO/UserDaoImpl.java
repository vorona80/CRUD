package com.vorona.CRUD.DAO;

import com.vorona.CRUD.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository

public class UserDaoImpl implements UserDao {
    private EntityManager entityManager;
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<User> getUser(/*int count*/) {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }
    public User show(int id) {
        TypedQuery<User> typedQuery = entityManager.createQuery("select user from User user where user.id = :id",User.class);
        typedQuery.setParameter("id",id);
        return typedQuery.getResultList().stream().findAny().orElse(null);
    }

    public void save(User user) {
        entityManager.persist(user);
    }
    public void update(int id, User updareUser) {
        User updateToUser = entityManager.getReference(User.class,id);
        updateToUser.setName(updareUser.getName());
        updateToUser.setSurname(updareUser.getSurname());
        updateToUser.setAge(updareUser.getAge());
        entityManager.merge(updateToUser);
    }
    public void delete(int id) {
        entityManager.remove(entityManager.getReference(User.class,id));
    }
}
