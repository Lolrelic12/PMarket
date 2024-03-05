/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO extends DBContext {
    public boolean accountExists(String username) {
        int count = 0;
        String query = "SELECT COUNT(admin_id) as matching_ids FROM [admin_account] WHERE username = '" + username + "'";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                count = rs.getInt("matching_ids");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    public int verifyLogin(String username, String passwordHash) {
        if (accountExists(username)) {
            String checkHash = "";
            String query = "SELECT password_hash FROM [admin_account] WHERE username = '" + username + "'";
            try {
                PreparedStatement st = connection.prepareStatement(query);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    checkHash = rs.getString("password_hash");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

            if (passwordHash.equals(checkHash)) {
                return 1;
            } else if (!passwordHash.equals(checkHash)) {
                return 0;
            }
        }
        return -1;
    }
}
