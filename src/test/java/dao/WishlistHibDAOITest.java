package dao;

import entity.WishlistItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class WishlistHibDAOITest {
    private WishlistDAO wishlistDAO = new WishlistHibDAOImpl();
    private WishlistItem wishlistItem, wishlistItem2;

    @Before
    public void onSetup() {
        wishlistItem = wishlistDAO.addItem(12345, "00011-test");
        wishlistItem2 = wishlistDAO.addItem(12345, "00012-test");
    }

    @After
    public void after() {
        wishlistDAO.deleteItem(wishlistItem.getId());
        wishlistDAO.deleteItem(wishlistItem2.getId());
    }

    @Test
    public void shouldReturnItem_whenItemIdExists() {
        assertEquals(wishlistItem.getId(), wishlistDAO.getItem(wishlistItem.getId()).getId());
    }

    @Test
    public void shouldReturnNull_whenItemIdDoesntExist() {
        assertNull(wishlistDAO.getItem(11111111));
    }

    @Test
    public void shouldReturnItem_whenItemProductCustomerExists() {
        assertNotNull(wishlistDAO.getItemByCustomerAndProductId(12345, "00011-test"));
    }

    @Test
    public void shouldAddItem() {
        assertNull(wishlistDAO.getItemByCustomerAndProductId(123456, "123456-test"));
        WishlistItem wishlistItemAdded = wishlistDAO.addItem(123456, "123456-test");
        assertNotNull(wishlistDAO.getItemByCustomerAndProductId(123456, "123456-test"));

        //delete item after test
        wishlistDAO.deleteItem(wishlistItemAdded.getId());
    }

    @Test
    public void shouldDeleteItem() {
        WishlistItem wishlistItemAdded = wishlistDAO.addItem(123456, "123456-test");
        assertNotNull(wishlistDAO.getItemByCustomerAndProductId(123456, "123456-test"));
        wishlistDAO.deleteItem(wishlistItemAdded.getId());
        assertNull(wishlistDAO.getItemByCustomerAndProductId(123456, "123456-test"));
    }

    @Test
    public void shouldReturnItemsListForCustomer() {
        assertTrue(wishlistDAO.findAllItemsForCustomer(12345).size() >= 2);
    }
}
