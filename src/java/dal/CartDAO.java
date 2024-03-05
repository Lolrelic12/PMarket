package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Product;

public class CartDAO extends DBContext {

    public void addCart(int accountId) {
        String query = "INSERT INTO cart VALUES (" + String.valueOf(accountId) + ")";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public int getCartId(int accountId) {
        int cartId = 0;
        String query = "SELECT cart_id FROM cart WHERE account_id = " + String.valueOf(accountId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                cartId = rs.getInt("cart_id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return cartId;
    }

    public Cart getCart(int accountId) {
        Cart cart = null;
        String query = "SELECT cart_id FROM cart WHERE account_id = " + String.valueOf(accountId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                cart = new Cart(rs.getInt("cart_id"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return cart;
    }

    public ArrayList<Product> getCartItems(int accountId) {
        int cartId = getCartId(accountId);
        ArrayList<Product> itemList = new ArrayList();
        String query = "select p.product_id, p.[name] as product_name, p.[description] as product_description, p.image_link, pc.[name] as category_name, p.price, cd.[count] "
                + "from cart_detail cd "
                + "left join product p on cd.product_id = p.product_id "
                + "left join product_category pc on p.product_category_id = pc.product_category_id "
                + "where cd.cart_id = " + String.valueOf(cartId) + " "
                + "order by cd.date_added desc";
        
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getString("product_description"), rs.getString("image_link"), rs.getString("category_name"), rs.getFloat("price"), rs.getInt("count"));
                itemList.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return itemList;
    }

    public void addToCart(int accountId, int productId, int count) {
        String query = "INSERT INTO cart_detail (cart_id, [count], date_added, product_id) VALUES (" + String.valueOf(getCartId(accountId)) + ", " + String.valueOf(count) + ", '" + LocalDateTime.now().toLocalDate() + "', " + String.valueOf(productId) + ")";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public float getCartTotal(int accountId) {
        int cartId = getCartId(accountId);
        float total = 0;
        String query = "SELECT SUM(cd.count * p.price) AS total_amount FROM cart_detail cd LEFT JOIN cart c on cd.cart_id = c.cart_id LEFT JOIN product p ON cd.product_id = p.product_id  WHERE c.cart_id = " + String.valueOf(cartId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                total = rs.getFloat("total_amount");

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return total;
    }

    public void modifyItemCount(int cartId, int productId, int change) {
        if (getItemCount(cartId, productId) + change <= 0) {
            removeFromCart(cartId, productId);
            return;
        }
        
        String query = "update cart_detail set count = count + " + String.valueOf(change) + " from cart_detail cd left join cart c on cd.cart_id = c.cart_id where cd.cart_id = " + String.valueOf(cartId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public int getCartDetailId(int cartId, int productId) {
        int cartDetailId = 0;
        String query = "select cart_detail_id from cart_detail where cart_id = " + String.valueOf(cartId) + " AND product_id = " + String.valueOf(productId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                cartDetailId = rs.getInt("cart_detail_id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return cartDetailId;
    }

    public void removeFromCart(int cartId, int productId) {
        int cartDetailId = getCartDetailId(cartId, productId);
        String query = "DELETE FROM cart_detail WHERE cart_detail_id = " + String.valueOf(cartDetailId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void emptyCart(int cartId) {
        String query = "DELETE FROM cart_detail WHERE cart_id = " + String.valueOf(cartId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getItemCount(int cartId, int productId) {
        int count = 0;
        String query = "SELECT cd.count FROM cart_detail cd LEFT JOIN cart c ON cd.cart_id = c.cart_id WHERE c.cart_id = " + String.valueOf(cartId) + "AND cd.product_id = " + String.valueOf(productId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return count;
    }
    
    public void setItemCount(int cartId, int productId, int count) {
        String query = "UPDATE cart_detail set count = " + String.valueOf(count) + " FROM cart_detail cd LEFT JOIN cart c ON cd.cart_id = c.cart_id WHERE c.cart_id = " + String.valueOf(cartId) + "AND cd.product_id = " + String.valueOf(productId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
