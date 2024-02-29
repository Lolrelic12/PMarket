package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;

public class AccountDAO extends DBContext {
    
    public List<Account> getAllAccounts() {
        List<Account> list = new ArrayList<>();
        String sql = "select * from account order by account_id";
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
    
    public Account getAccount(int accountId) {
        Account account = null;
        String query = "select * from account where id = " + String.valueOf(accountId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("display_name"), rs.getString("email"), rs.getBoolean("verified"), rs.getFloat("balance"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return account;
    }
    
    public void addAccount(Account account, String passwordHash) {
        String query = "insert into account values ('" + account.getAccountId() + "', '" + account.getUsername() + "', '" + passwordHash + "', '" + account.getDisplayName() + "', '" + account.getEmail() + "', 'false', 0";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    // incomplete
    public void updateAccount(Account account) {
        String query = "select * from account where id = " + String.valueOf(account.getAccountId()); 
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("display_name"), rs.getString("email"), rs.getBoolean("verified"), rs.getFloat("balance"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void changePassword(int accountId, String passwordHash) {
        String query = "update account set password_hash = '" + passwordHash + "' where account_id = " + String.valueOf(accountId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void changeEmail(int accountId, String email) {
        String query = "update account set email = '" + email + "' where account_id = " + String.valueOf(accountId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void changeDisplayName(int accountId, String displayName) {
        String query = "update account set display_name = '" + displayName + "' where account_id = " + String.valueOf(accountId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
}
