package dao;

import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class UserHibDaoImpl implements  UserDAO {

    @Override
    public List<User> getUsersByEmail(String email){
        Query query = callEntityManager().createQuery("select u from User as u where u.email = :paramEmail");
        query.setParameter("paramEmail", email);
        List<User> user = query.getResultList();
        return user;
    }

    @Override
    public User addNewUser(String name, String email, String role){
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

    private EntityManager callEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("user");
        return factory.createEntityManager();
    }
}
