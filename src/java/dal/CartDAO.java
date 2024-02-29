package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class CartDAO extends DBContext {

    public List<Product> getCart(int accountId) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT p.product_id, p.name AS product_name, p.price, cd.count FROM cart_detail cd JOIN cart c ON cd.cart_id = c.cart_id JOIN product p ON cd.product_id = p.product_id WHERE c.account_id = " + String.valueOf(accountId) + " ORDER BY date_added DESC;";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cart Cart = new Product(rs.getInt("cart_id"), rs.getString("username"), rs.getString("display_name"), rs.getString("email"), rs.getBoolean("verified"), rs.getFloat("balance"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return ;
    }
}
