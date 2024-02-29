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
    private ArrayList<HashMap> purchaseHistory;
    HashMap<Product, LocalDateTime> entry;
    
    public PurchaseHistory() {}
    
    public PurchaseHistory(int purchaseHistoryId) {
        this.purchaseHistoryId = purchaseHistoryId;
        purchaseHistory = new ArrayList();
        
    }
    
    public int getPurchaseHistoryId() {
        return purchaseHistoryId;
    }
    
    public void setPurchaseHistoryId(int id) {
        this.purchaseHistoryId = id;
    }
    
    public ArrayList<HashMap> getAllEntries() {
        return purchaseHistory;
    }
    
    public void addEntry(Product product, LocalDateTime date) {
        entry = new HashMap();
        entry.put(product, date);
        purchaseHistory.add(entry);
    }
}
