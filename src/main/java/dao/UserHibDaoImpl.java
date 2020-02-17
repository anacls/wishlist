package dao;

import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class UserHibDaoImpl {

    public List<User> getUserByEmail(String email){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("user");
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createQuery("select u from User as u where u.email = :paramEmail");
        query.setParameter("paramEmail", email);
        List<User> user = query.getResultList();
        return user;
    }

    public User addNewUser(String name, String email, String role){
        User user = new User();
        user.setName(name);
        user.setRole(role);
        user.setEmail(email);
        callEntityManager(user);
        return user;
    }

    private void callEntityManager(User user) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("user");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();
        manager.close();
    }
}
