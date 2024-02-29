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
public class Order {
    private int orderId;
    private ArrayList<Product> orderItems;
    private LocalDateTime dateCreated;
    private LocalDateTime dateCompleted;
    private float paymentAmount;
    private boolean paid;
    private LocalDateTime datePaid;
    private boolean completed;

    public Order(int orderId, LocalDateTime dateCreated, LocalDateTime dateCompleted, float paymentAmount, boolean paid, LocalDateTime datePaid, boolean completed) {
        this.orderId = orderId;
        this.orderItems = new ArrayList();
        this.dateCreated = dateCreated;
        this.dateCompleted = dateCompleted;
        this.paymentAmount = paymentAmount;
        this.paid = paid;
        this.datePaid = datePaid;
        this.completed = completed;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public ArrayList<Product> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<Product> orderItems) {
        this.orderItems = orderItems;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(LocalDateTime dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount() {
        float total = 0;
        for (Product p : orderItems) {
            paymentAmount += p.getPrice() * p.getStock();
        }
        
        this.paymentAmount = total;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public LocalDateTime getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(LocalDateTime datePaid) {
        this.datePaid = datePaid;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    public ArrayList<Product> getItems() {
        return orderItems;
    }

    public void addItem(Product p) {
        orderItems.add(p);
    }

    public void removeItem(Product p) {
        orderItems.remove(p);
    }

    public void removeItem(int productId) {
        int index = getItemIndex(productId);
        if (index != -1) {
            orderItems.remove(index);
        }
    }

    public int getItemIndex(int productId) {
        for (int i = 0; i < orderItems.size(); i++) {
            if (orderItems.get(i).getProductId() == productId) {
                return i;
            }
        }
        return -1;
    }

    public void modifyItemCount(int productId, int change) {
        int index = getItemIndex(productId);
        if (index != -1) {
            Product p = orderItems.get(index);
            p.modifyStock(change);
            if (p.getStock() < 0) {
                p.setStock(0);
            }
        }
    }
    
    public float getTotalPaymentAmount() {
        float paymentAmount = 0;
        for (Product p : orderItems) {
            paymentAmount += p.getPrice() * p.getStock();
        }
        
        return paymentAmount;
    }

    public int getItemCount(int productId) {
        int index = getItemIndex(productId);
        if (index == -1) {
            return 0;
        }
        return orderItems.get(index).getStock();
    }

    public boolean isEmpty() {
        return orderItems.isEmpty();
    }

}

