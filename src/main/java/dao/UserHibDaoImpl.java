package dao;

import entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class UserHibDaoImpl implements UserDAO {
    @Override
    public User findUserById(int id) {
        Query query = callEntityManager().createQuery("select u from User as u where u.id = :paramId");
        query.setParameter("paramId", id);
        List<User> users = query.getResultList();
        if(users.size() > 0) { return users.get(0); }
        else { return null; }
    }

    @Override
    public User findUserByEmail(String email) {
        Query query = callEntityManager().createQuery("select u from User as u where u.email = :paramEmail");
        query.setParameter("paramEmail", email);
        List<User> users = query.getResultList();
        if(users.size() > 0) { return users.get(0); }
        else { return null; }
    }

    @Override
    public User addNewUser(String name, String email, String role) {
        User user = new User();
        user.setName(name);
        user.setRole(role);
        user.setEmail(email);
        EntityManager manager = callEntityManager();
        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();
        manager.close();
        return user;
    }

    @Override
    public List<User> findAll() {
        Query query = callEntityManager().createQuery("from User");
        List<User> users = query.getResultList();
        return users;
    }

    @Override
    public void deleteUser(int id) {
        EntityManager manager = callEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createQuery("DELETE from User u where u.id= :paramId");
        query.setParameter("paramId", id);
        query.executeUpdate();
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public User updateUser(int id, String name, String email) {
        EntityManager manager = callEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createQuery("update User u set u.name= :paramName, u.email= :paramEmail where u.id= :paramId");
        query.setParameter("paramName", name);
        query.setParameter("paramEmail", email);
        query.setParameter("paramId", id);
        query.executeUpdate();
        manager.getTransaction().commit();
        manager.close();
        return findUserById(id);
    }

    private EntityManager callEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("user");
        return factory.createEntityManager();
    }
}
