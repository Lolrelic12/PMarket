/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class PurchaseHistory {
    private int puchaseHistoryId;
    private ArrayList<Integer> entries;
    
    public PurchaseHistory() {}
    
    public PurchaseHistory(int purchaseHistoryId) {
        this.puchaseHistoryId = purchaseHistoryId;
    }
}
