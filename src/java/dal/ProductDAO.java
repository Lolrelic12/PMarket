package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class ProductDAO extends DBContext {

    public Product getProduct(int productId) {
        Product product = null;
        String query = "SELECT p.product_id, p.[name] as product_name, p.[description] as product_description, p.image_link, pc.[name] as category_name, p.price, p.sale, p.stock FROM product p LEFT JOIN product_category pc ON p.product_category_id = pc.product_category_id WHERE product_id = " + String.valueOf(productId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                product = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getString("description"), rs.getString("imageLink"), rs.getString("category_name"), rs.getFloat("price"), rs.getFloat("sale"), rs.getInt("stock"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return product;
    }
    
    public String getCategoryName(int categoryId) {
        String categoryName = "";
        String query = "SELECT FROM  WHERE product_id = " + String.valueOf(categoryId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                product = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getString("description"), rs.getString("imageLink"), rs.getString("category_name"), rs.getFloat("price"), rs.getFloat("sale"), rs.getInt("stock"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return product;
    }

    public List<Product> getAllProduct() {
        ArrayList<Product> productList = new ArrayList();
        String query = "SELECT p.product_id, p.[name] as product_name, p.[description] as product_description, p.image_link, pc.[name] as category_name, p.price, p.sale, p.stock FROM product p LEFT JOIN product_category pc ON p.product_category_id = pc.product_category_id WHERE product_id = " + String.valueOf(productId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getString("description"), rs.getString("imageLink"), rs.getString("category_name"), rs.getFloat("price"), rs.getFloat("sale"), rs.getInt("stock"));
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return productList;
    }

    public void addProduct(Product product) {
        String query = "insert into product values ('" + product.getProductId()+ "', '" + "', '" + product.getName() + "', '" + product.getDescription() + "', '" + prod + "', '" + account.getEmail() + "', 'false', 0";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Product> getCartItems(int accountId) {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT p.product_id, p.name AS product_name, p.image_link, p.price, p.rating, cd.count FROM cart_detail cd JOIN cart c ON cd.cart_id = c.cart_id JOIN product p ON cd.product_id = p.product_id WHERE c.account_id = " + String.valueOf(accountId) + " ORDER BY date_added DESC;";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getString("description"), rs.getString("image_link"), rs.getBoolean("verified"), rs.getFloat("balance"));
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return productList;
    }
}
