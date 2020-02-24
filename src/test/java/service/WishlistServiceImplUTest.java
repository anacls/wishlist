package service;

import dao.WishlistDAO;
import entity.WishlistItem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class WishlistServiceImplUTest {

    @InjectMocks
    private WishlistService wishlistService = new WishlistServiceImpl();

    @Mock
    WishlistDAO wishlistDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCallDAOAddItemMethod_whenProductExists() throws IOException {
        wishlistService.addItem(1234, "1579762e-cfc3-5f20-afb7-8208ea92cbd1");
        verify(wishlistDAO, times(1)).addItem(1234, "1579762e-cfc3-5f20-afb7-8208ea92cbd1");
    }

    @Test
    public void shouldntCallDAOAddItemMethod_whenProductDoesntExist() throws IOException {
        wishlistService.addItem(1234, "1579762e");
        verify(wishlistDAO, times(0)).addItem(1234, "1579762e");
    }

    @Test
    public void shouldntCallDAOAddItemMethod_whenProductAlreadyExistsOnList() throws IOException{
        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setCustomerId(1234);
        wishlistItem.setProductId("ff714015-38e3-2d95-ee5a-e8010a61b2e2");
        when(wishlistService.getItemByCustomerAndProductId(1234,
                                                    "ff714015-38e3-2d95-ee5a-e8010a61b2e2"))
                                                            .thenReturn(wishlistItem);
        wishlistService.addItem(12234, "ff714015-38e3-2d95-ee5a-e8010a61b2e2");
        verify(wishlistDAO, times(0)).addItem(1234, "ff714015-38e3-2d95-ee5a-e8010a61b2e2");
    }

    @Test
    public void shouldCallDAOGetItemMethod() {
        wishlistService.getItem(1234);
        verify(wishlistDAO, times(1)).getItem(1234);
    }

    @Test
    public void shouldCallDAOGetItemByCustomerAndProductId() {
        wishlistService.getItemByCustomerAndProductId(1223, "2313122");
        verify(wishlistDAO, times(1)).getItemByCustomerAndProductId(1223, "2313122");
    }

    @Test
    public void shouldCallDAODeleteItemMethod() {
        WishlistItem wishlistItem = new WishlistItem();
        when(wishlistService.getItem(1234)).thenReturn(wishlistItem);
        wishlistService.deleteItem(1234);
        verify(wishlistDAO, times(1)).deleteItem(1234);
    }

    @Test
    public void shouldCallDAOFindAllItemsForCustomer() {
        wishlistService.findAllItemsForCustomer(1234);
        verify(wishlistDAO, times(1)).findAllItemsForCustomer(1234);
    }
}
