package dao;

import entity.WishlistItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class WishlistHibDAOImpl implements WishlistDAO {
    @Override
    public WishlistItem addItem(int customerId, String productId){
        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setCustomerId(customerId);
        wishlistItem.setProductId(productId);
        EntityManager manager = callEntityManager();
        manager.getTransaction().begin();
        manager.persist(wishlistItem);
        manager.getTransaction().commit();
        manager.close();
        return wishlistItem;
    }

    @Override
    public WishlistItem getItem(int id){
        Query query = callEntityManager().createQuery("select i from WishlistItem as i where i.id = :paramId");
        query.setParameter("paramId", id);
        List<WishlistItem> wishlistItems = query.getResultList();
        if(wishlistItems.size() > 0) { return wishlistItems.get(0); }
        else { return null; }
    }

    @Override
    public WishlistItem getItemByCustomerAndProductId(int customerId, String productId){
        Query query = callEntityManager().createQuery("select i from WishlistItem as i where i.customerId = :paramCustomerId AND i.productId= :paramProductId");
        query.setParameter("paramCustomerId", customerId);
        query.setParameter("paramProductId", productId);
        List<WishlistItem> wishlistItems = query.getResultList();
        if(wishlistItems.size() > 0) { return wishlistItems.get(0); }
        else { return null; }
    }

    @Override
    public void deleteItem(int id){
        EntityManager manager = callEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createQuery("DELETE from WishlistItem i where i.id= :paramId");
        query.setParameter("paramId", id);
        query.executeUpdate();
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<WishlistItem> findAllItemsForCustomer(int customerId) {
        Query query = callEntityManager().createQuery("select i from WishlistItem as i where i.customerId= :paramCustomerId");
        query.setParameter("paramCustomerId", customerId);
        List<WishlistItem> wishlistItem = query.getResultList();
        return wishlistItem;
    }

    private EntityManager callEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("wishlistItem");
        return factory.createEntityManager();
    }
}
