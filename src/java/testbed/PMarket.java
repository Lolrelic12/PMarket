/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testbed;

import ultility.Email;
import ultility.KeyGenerator;
import security.Hash;
import java.time.LocalDateTime;

/**
 *
 * @author admin
 */
public class PMarket {
    public static void main(String[] args) {
        String key = KeyGenerator.generateKey();
        String content = "Key: " + key + "\nKey hash: " + Hash.getHash(key, "SHA-256") + "\nSent at: " + LocalDateTime.now();
        Email.sendMail("PMarket", "conboquangdeptrai2@gmail.com", "Your product key is ready!", content);        
    }
}
