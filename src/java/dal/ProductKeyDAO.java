package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductKeyDAO extends DBContext{
    public String getProductKey(int productId) {
        String productKey = "";
        String query = "get pk.product_key from product_key pk left join product p on pk.product_id = p.product_id where p.product_id = " + String.valueOf(productId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                productKey = rs.getString("product_key");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return productKey;
    }
    
    public int getProductKeyId(int productId) {
        int productKeyId = 0;
        String query = "get pk.product_key from product_key pk left join product p on pk.product_id = p.product_id where p.product_id = " + String.valueOf(productId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                productKeyId = rs.getInt("product_key_id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return productKeyId;
    }
    
    
    public void addProductKey(int productId, String productKey) {
        String query = "insert into product_key values (" + String.valueOf(productId) + ", '" + productKey + "', " + "'true'";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void setValidity(int productId, boolean validity) {
        String query = "update pk.product_key set validity = '" + String.valueOf(validity) + "' from product_key pk left join product p on pk.product_id = p.product_id where p.product_id = " + String.valueOf(productId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
