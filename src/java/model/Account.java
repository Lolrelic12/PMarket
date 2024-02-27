/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class Account {
    private int accountId;
    private String username;
    // password to be passed as hash
    private String displayName;
    private String email;
    private boolean verified;
    private float balance;
    private Cart cart;
    private PurchaseHistory purchaseHistory;
    
    public Account() {}

    public Account(int accountId, String username, String displayName, String email, boolean verified, float balance) {
        this.accountId = accountId;
        this.username = username;
        this.displayName = displayName;
        this.email = email;
        this.verified = verified;
        this.balance = balance;
        this.cart = new Cart();
        this.purchaseHistory = new PurchaseHistory();
    }

    public int getAccountId() {
        return accountId;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isVerified() {
        return verified;
    }

    public float getBalance() {
        return balance;
    }

    public Cart getCart() {
        return cart;
    }

    public PurchaseHistory getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
    
    public void modifyBalance(float value) {
        this.balance += value;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setPurchaseHistory(PurchaseHistory purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }    
        
}
