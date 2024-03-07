/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.Product;
import utility.DateConverter;

/**
 *
 * @author admin
 */
public class OrderDAO extends DBContext {

    public void addOrder(int accountId, LocalDateTime dateCreated) {
        String query = "INSERT INTO order (account_id, date_created) VALUES (" + String.valueOf(accountId) + ", '" + DateConverter.convertToDateViaInstant(dateCreated) + "')";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void addOrderItems(int orderId, ArrayList<Product> productList) {
        int listLength = productList.size();
        String query = "INSERT INTO order_detail VALUES (" + String.valueOf(orderId) + ", ";
        for (int i = 0; i < listLength - 1; i++) {
            Product p = productList.get(i);
            query += String.valueOf(p.getProductId()) + ", " + String.valueOf(p.getStock()) + "), (";
        }
        Product p = productList.get(listLength - 1);
        query += String.valueOf(p.getProductId()) + ", " + String.valueOf(p.getStock()) + ")";
        
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    

    public ArrayList<Order> getOrders(int accountId) {
        ArrayList<Order> orderList = new ArrayList();
        String query = "select * from [order] o left join payment p on o.order_id = p.order_id where o.account_id = " + String.valueOf(accountId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt("order_id"), DateConverter.convertToLocalDateTimeViaInstant(rs.getDate("date_created")), rs.getFloat("payment_amount"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return orderList;
    }
    
    public ArrayList<Product> getOrderItems(int orderId) {
        ArrayList<Product> itemList = new ArrayList();
        String query = "select p.product_id, p.[name] as product_name, p.[description] as product_description, p.image_link, pc.[name] as category_name, p.price, od.count "
                + "from order_detail od left join [order] o on od.order_id = o.order_id "
                + "left join product p on od.product_id = p.product_id "
                + "left join product_category pc on p.product_category_id = pc.product_category_id "
                + "where o.order_id = '" + String.valueOf(orderId) + "' "
                + "order by o.date_created desc";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getString("description"), rs.getString("imageLink"), rs.getString("category_name"), rs.getFloat("price"), rs.getInt("stock"));
                itemList.add(product);;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return itemList;
    }
    
    public int getPaymentId(int orderId) {
        int paymentId = 0;
        String query = "select payment_id from payment where payment_id = " + String.valueOf(orderId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                paymentId = rs.getInt("payment_id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return paymentId;
    }
    
    public float getPaymentAmount(int orderId) {
        float paymentAmount = 0;
        String query = "select amount from payment where order_id = " + String.valueOf(orderId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                paymentAmount = rs.getFloat("payment_id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return paymentAmount;
    }

    public boolean getOrderStatus(int orderId) {
        boolean orderStatus = false;
        String query = "select completed from [order] where order_id = " + String.valueOf(orderId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                orderStatus = rs.getBoolean("completed");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return orderStatus;
    }
    
    public boolean getPaymentStatus(int orderId) {
        boolean paymentStatus = false;
        String query = "select completed from payment where order_id = " + String.valueOf(orderId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                paymentStatus = rs.getBoolean("completed");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return paymentStatus;
    }
    
    public void setOrderStatus(int orderId, boolean status) {
        String query = "update [order] set completed = '" + String.valueOf(status) + "' where order_id = " + String.valueOf(orderId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void setPaymentStatus(int orderId, boolean status) {
        String query = "update payment set completed = '" + String.valueOf(status) + "' where order_id = " + String.valueOf(orderId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
