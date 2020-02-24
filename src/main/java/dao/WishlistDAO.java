package dao;

import entity.WishlistItem;
import java.util.List;

public interface WishlistDAO {
    WishlistItem addItem(int customerId, String productId);
    WishlistItem getItem(int id);
    void deleteItem(int id);
    List<WishlistItem> findAllItemsForCustomer(int customerId);
    WishlistItem getItemByCustomerAndProductId(int customerId, String productId);
}
