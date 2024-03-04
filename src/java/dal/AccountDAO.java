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

    public Account getAccountById(int accountId) {
        Account account = null;
        String query = "select * from account where account_id = " + String.valueOf(accountId);
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
    
    public Account getAccountByUsername(String username) {
        Account account = null;
        String query = "select * from account where username = " + username;
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
    
    public int getAccountIdByUsername(String username) {
        int accountId = 0;
        String query = "select account_id from account where username = '" + username + "'";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                accountId = rs.getInt("account_id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return accountId;
    }

    public void addAccount(Account account, String passwordHash) {
        String query = "insert into account (username, password_hash, display_name, email, verified, balance) values ('" + account.getUsername() + "', '" + passwordHash + "', N'" + account.getDisplayName() + "', '" + account.getEmail() + "', 'false', 0)";
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

    public float getBalance(int accountId) {
        float balance = 0;
        String query = "select balance from account where account_id = " + String.valueOf(accountId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                balance = rs.getFloat("balance");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return balance;
    }

    public void modifyBalance(int accountId, float change) {
        if (getBalance(accountId) + change <= 0) {
            setBalance(accountId, 0);
            return;
        }

        String query = "update account set balance = balance + " + String.valueOf(change) + " where account_id = " + String.valueOf(accountId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void setBalance(int accountId, float balance) {
        String query = "update account set balance = " + String.valueOf(balance) + " where account_id = " + String.valueOf(accountId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean accountExists(String username) {
        int count = 0;
        String query = "SELECT COUNT(account_id) as matching_ids FROM account WHERE username = '" + username + "';";
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
    
    public boolean emailExists(String email) {
        int count = 0;
        String query = "SELECT COUNT(account_id) as matching_ids FROM account WHERE email = '" + email + "';";
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
            String query = "SELECT password_hash FROM account WHERE username = '" + username + "'";
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
    
    public void setVerifiedStatus(int accountId, boolean verified) {
        String query = "update account set verified = '" + verified +"' where account_id = " + String.valueOf(accountId);
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
