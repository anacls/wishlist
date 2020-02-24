package controller;

import entity.WishlistItem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.WishlistService;
import spark.Request;
import spark.Response;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class WishlistApiUTest {
    @InjectMocks
    WishListApi wishListApi = new WishListApi();

    @Mock
    Request request;

    @Mock
    Response response;

    @Mock
    WishlistService wishlistService;

    @Before
    public void onSetup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnProductAddedSuccessfully_whenAddItem() throws IOException {
        WishlistItem wishlistItem = new WishlistItem();
        when(wishlistService.addItem(1234, "2308098")).thenReturn(wishlistItem);
        when(request.queryParams("user_id")).thenReturn("1234");
        when(request.queryParams("product_id")).thenReturn("2308098");
        assertEquals("Produto adicionado aos favoritos", wishListApi.addItemToWishlist(request, response));
    }

    @Test
    public void shouldReturnErrorMessage_whenIsntPossibleAddItem() throws IOException {
        when(request.queryParams("user_id")).thenReturn("1234");
        when(request.queryParams("product_id")).thenReturn("2308098");
        assertEquals("Não foi possível adicionar produto aos favoritos", wishListApi.addItemToWishlist(request, response));
    }

    @Test
    public void shouldReturnErrorMessage_whenTryingReturnUnexistentItem() {
        when(request.params("id")).thenReturn("1234");
        assertEquals("Item não existente na lista de favoritos", wishListApi.getItemFromWishlist(request, response));
    }

    @Test
    public void shouldReturnItemInfo_whenItemExists() {
        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setId(1234);
        wishlistItem.setProductId("123456789");
        wishlistItem.setCustomerId(1234);
        when(request.params("id")).thenReturn("1234");
        when(wishlistService.getItem(1234)).thenReturn(wishlistItem);
        assertEquals("Item\n id: 1234, Cliente: 1234", wishListApi.getItemFromWishlist(request, response));
    }

    @Test
    public void shouldReturnErrorMessage_whenIsntPossibleDeleteItem() {
        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setId(1234);
        when(wishlistService.getItem(1234)).thenReturn(wishlistItem);
        when(request.params("id")).thenReturn("1234");
        assertEquals("Não foi possível excluir item", wishListApi.deleteItemFromWishlist(request, response));
    }

    @Test
    public void shouldReturnItemDeletedSuccessfully_whenDeleteItem() {
        when(wishlistService.getItem(1234)).thenReturn(null);
        when(request.params("id")).thenReturn("1234");
        assertEquals("Item excluído com sucesso", wishListApi.deleteItemFromWishlist(request, response));
    }

    @Test
    public void shouldReturnWishlistForACustomer() {
        WishlistItem wishlistItem1 = new WishlistItem();
        WishlistItem wishlistItem2 = new WishlistItem();
        wishlistItem1.setId(1234);
        wishlistItem1.setCustomerId(1234567);
        wishlistItem1.setProductId("12-test");
        wishlistItem2.setId(13232);
        wishlistItem2.setCustomerId(1234567);
        wishlistItem2.setProductId("1234-test");

        List<WishlistItem> wishlistItems = new LinkedList<>();
        wishlistItems.add(wishlistItem1);
        wishlistItems.add(wishlistItem2);

        when(wishlistService.findAllItemsForCustomer(1234567)).thenReturn(wishlistItems);
        when(request.params("id")).thenReturn("1234567");
        assertEquals("Favoritos\nProduto: 12-test\nProduto: 1234-test\n", wishListApi.findAllItemsFromAWishlist(request));
    }
}
