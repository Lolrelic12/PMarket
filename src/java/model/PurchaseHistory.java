/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author admin
 */
public class PurchaseHistory {
    private int purchaseHistoryId;
    HashMap<Integer, LocalDateTime> purchaseHistory;
    
    public PurchaseHistory() {}
    
    public PurchaseHistory(int purchaseHistoryId) {
        this.purchaseHistoryId = purchaseHistoryId;
        purchaseHistory = new HashMap();
        
    }
    
    public int getPurchaseHistoryId() {
        return purchaseHistoryId;
    }
    
    public void setPurchaseHistoryId(int id) {
        this.purchaseHistoryId = id;
    }
    
    public HashMap<Integer, LocalDateTime> getAllEntries() {
        return purchaseHistory;
    }
    
    public void addEntry(int productId, LocalDateTime date) {
        purchaseHistory.put(productId, date);
    }
}
