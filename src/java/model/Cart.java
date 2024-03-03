package model;

import java.util.ArrayList;

public class Cart {

    private int cartId;
    private ArrayList<Product> cartItems;
    
    public Cart() {}

    public Cart(int cartId) {
        this.cartId = cartId;
        cartItems = new ArrayList();
    }

    public int getId() {
        return cartId;
    }
    
    public void setId(int cartId) {
        this.cartId = cartId;
    }

    public ArrayList<Product> getItems() {
        return cartItems;
    }

    public void addItem(Product p) {
        cartItems.add(p);
    }

    public void removeItem(Product p) {
        cartItems.remove(p);
    }

    public void removeItem(int productId) {
        int index = getItemIndex(productId);
        if (index != -1) {
            cartItems.remove(index);
        }
    }

    public int getItemIndex(int productId) {
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getProductId() == productId) {
                return i;
            }
        }
        return -1;
    }

    public void modifyItemCount(int productId, int change) {
        int index = getItemIndex(productId);
        if (index != -1) {
            Product p = cartItems.get(index);
            p.modifyStock(change);
            if (p.getStock() < 0) {
                p.setStock(0);
            }
        }
    }

    public int getItemCount(int productId) {
        int index = getItemIndex(productId);
        if (index == -1) {
            return 0;
        }
        return cartItems.get(index).getStock();
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }
    
    public void setCart(ArrayList<Product> itemList) {
        this.cartItems = itemList;
    }

}
