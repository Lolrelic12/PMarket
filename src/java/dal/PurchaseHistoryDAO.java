/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Product;
import model.PurchaseHistory;
import java.time.LocalDateTime;
import ultility.DateConverter;

public class PurchaseHistoryDAO extends DBContext {
    
    public void addHistory(int accountId) {
        String query = "INSERT INTO purchase_history VALUES (" + String.valueOf(accountId) + ")";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public int getPurchaseHistoryId(int accountId) {
        int purchaseHistoryId = 0;
        String query = "SELECT purchase_history_id FROM purchase_history WHERE account_id = " + String.valueOf(accountId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                purchaseHistoryId = rs.getInt("purchase_history_id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return purchaseHistoryId;
    }

    public HashMap<Integer, LocalDateTime> getHistory(int purchaseHistoryId) {
        HashMap<Integer, LocalDateTime> history = new HashMap();
        String query = "SELECT product_id , date_bought FROM history_detail hd LEFT JOIN purchase_history ph ON hd.history_id = ph.purchase_history_id WHERE ";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                history.put(rs.getInt("product_id"), DateConverter.convertToLocalDateTimeViaInstant(rs.getDate("date_bought")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return history;
    }

    public void addEntry(int accountId, int productId, LocalDateTime dateBought) {
        String query = "INSERT INTO history_detail VALUES (" + getPurchaseHistoryId(accountId) + ", " + String.valueOf(productId) + ", '" + DateConverter.convertToDateViaInstant(dateBought) + "')";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
