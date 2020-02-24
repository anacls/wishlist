package service;

import entity.WishlistItem;

import java.io.IOException;
import java.util.List;

public interface WishlistService {
    WishlistItem addItem(int customerId, String productId) throws IOException;
    WishlistItem getItem(int id);
    WishlistItem getItemByCustomerAndProductId(int customerId, String productId);
    void deleteItem(int id);
    List<WishlistItem> findAllItemsForCustomer(int customerId);
}
