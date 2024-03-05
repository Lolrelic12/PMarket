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
        String query = "SELECT p.product_id, p.[name] as product_name, p.[description] as product_description, p.image_link, pc.[name] as category_name, p.price, p.stock FROM product p LEFT JOIN product_category pc ON p.product_category_id = pc.product_category_id WHERE product_id = " + String.valueOf(productId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                product = new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getString("product_description"), rs.getString("image_link"), rs.getString("category_name"),rs.getFloat("price"), 1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return product;
    }
    
    public String getCategoryName(int categoryId) {
        String categoryName = "";
        String query = "SELECT [name] FROM product_category WHERE product_category_id = " + String.valueOf(categoryId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                categoryName = rs.getString("name");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return categoryName;
    }
    
    public int getCategoryId(String categoryName) {
        int categoryId = 0;
        String query = "SELECT product_category_id FROM product_category WHERE [name] = '" + categoryName +'"';
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                categoryName = rs.getString("product_category_id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return categoryId;
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> productList = new ArrayList();
        String query = "SELECT p.product_id, p.[name] as product_name, p.[description] as product_description, p.image_link, pc.[name] as category_name, p.price, p.stock FROM product p LEFT JOIN product_category pc ON p.product_category_id = pc.product_category_id";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getString("product_description"), rs.getString("image_link"), rs.getString("category_name"), rs.getFloat("price"), 1);
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return productList;
    }
    
    public List<Product> getAllProductsOfCategory(int categoryId) {
        ArrayList<Product> productList = new ArrayList();
        String query = "SELECT p.product_id, p.[name] as product_name, p.[description] as product_description, p.image_link, pc.[name] as category_name, p.price, p.stock FROM product p LEFT JOIN product_category pc ON p.product_category_id = pc.product_category_id WHERE pc.product_category_id = " + String.valueOf(categoryId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getString("product_description"), rs.getString("image_link"), rs.getString("category_name"), rs.getFloat("price"), 1);
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return productList;
    }
    
    public List<Product> getAllProductsContaining(String token) {
        ArrayList<Product> productList = new ArrayList();
        String query = "SELECT p.product_id, p.[name] as product_name, p.[description] as product_description, p.image_link, pc.[name] as category_name, p.price, p.stock FROM product p LEFT JOIN product_category pc ON p.product_category_id = pc.product_category_id WHERE p.name LIKE '%" + token + "%'";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getString("product_description"), rs.getString("image_link"), rs.getString("category_name"), rs.getFloat("price"), 1);
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return productList;
    }

    public void addProduct(Product p) {
        String query = "insert into product ([name], [description], image_link, product_category_id, price, stock) values ('" + p.getName() + "', '" + p.getDescription() + "', '" + p.getImageLink() + "', " + String.valueOf(p.getCategoryId()) + ", " + p.getPrice() + ", 1)";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getStock(int productId) {
        int stock = 0;
        String query = "SELECT stock FROM product WHERE product_id = " + String.valueOf(productId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                stock = rs.getInt("stock");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return stock;
    }
    
    public void removeProduct(int productId) {
        String query = "DELETE FROM product WHERE product_id = " + String.valueOf(productId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
}
