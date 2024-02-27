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
import model.Account;

/**
 *
 * @author admin
 */
public class AccountDAO extends DBContext {
    
    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        String sql = "select * from account";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("display_name"), rs.getString("email"), rs.getBoolean("verified"), rs.getFloat("balance"));
                list.add(account);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
    
    public Account get(int id) {
        Account account = null;
        String sql = "select * from account where id = " + String.valueOf(id);
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("display_name"), rs.getString("email"), rs.getBoolean("verified"), rs.getFloat("balance"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return account;
    }
    
}
