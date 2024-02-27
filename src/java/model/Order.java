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
public class Order<Integer> extends ArrayList<Integer> {
    private int orderId;
    private LocalDateTime dateCreated;
    private LocalDateTime dateCompleted;
    private float paymentAmount;
    private boolean paid;
    private LocalDateTime datePaid;
    private boolean completed;
}
