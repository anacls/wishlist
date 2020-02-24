package service;

import dao.WishlistDAO;
import dao.WishlistHibDAOImpl;
import entity.WishlistItem;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class WishlistServiceImpl implements WishlistService {

    private WishlistDAO wishlistDao = new WishlistHibDAOImpl();

    @Override
    public WishlistItem addItem(int customerId, String productId) throws IOException {
        boolean productExists = isProductOnAPI(productId);
        if(productExists){
            if(getItemByCustomerAndProductId(customerId, productId) == null) {
                return wishlistDao.addItem(customerId, productId);
            }
        }
        return null;
    }

    @Override
    public WishlistItem getItem(int id){
        return wishlistDao.getItem(id);
    }

    @Override
    public WishlistItem getItemByCustomerAndProductId(int customerId, String productId){
        return wishlistDao.getItemByCustomerAndProductId(customerId, productId);
    }

    @Override
    public void deleteItem(int id){
        wishlistDao.deleteItem(id);
    }

    @Override
    public List<WishlistItem> findAllItemsForCustomer(int customerId){
        return wishlistDao.findAllItemsForCustomer(customerId);
    }

    private boolean isProductOnAPI(String productId) throws IOException{
        URL url = new URL("http://challenge-api.luizalabs.com/api/product/" + productId);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int status = con.getResponseCode();

        return status==200;
    }
}
