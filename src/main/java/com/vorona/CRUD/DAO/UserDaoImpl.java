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

    public List<User> getUser() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }
    public User show(long id) {
        TypedQuery<User> typedQuery = entityManager.createQuery("select user from User user where user.id = :id",User.class);
        typedQuery.setParameter("id",id);
        return typedQuery.getResultList().stream().findAny().orElse(null);
    }

    public void save(User user) {
        entityManager.persist(user);
    }
    public void update(long id, User updareUser) {
        entityManager.merge(updareUser);
//        User updateToUser = entityManager.getReference(User.class,id);
//        updateToUser.setName(updareUser.getName());
//        updateToUser.setSurname(updareUser.getSurname());
//        updateToUser.setAge(updareUser.getAge());
//        entityManager.merge(updateToUser);
    }
    public void delete(long id) {
        entityManager.remove(entityManager.getReference(User.class,id));
    }
}
