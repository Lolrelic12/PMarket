package com;

import java.time.LocalDateTime;
import model.Account;
import ultility.Email;

public class ContentDelivery {

    public static void sendProductKey(String emailAddress, String[] productNames, String[] productKeys) {
        String senderName = "PMarket";
        String toAddress = emailAddress;

        String subject = "Your product key is ready!";

        String content = "Congratulations on your purchase(s). You are now ready to activate your product(s) on Steam.\n\n";
        for (int i = 0; i < productNames.length; i++) {
            content += "Product name: " + productNames[i]
                    + "\nProduct key: " + productKeys[i] + "\n\n";
        }
        content += "Thank you for shopping with PMarket! \n\nSent at: " + LocalDateTime.now();

        Email.sendMail(senderName, toAddress, subject, content);
    }

    public static void sendPasswordResetRequest(String emailAddress, Account account, String code) {
        String senderName = "PMarket";
        String toAddress = emailAddress;

        String subject = "Password reset request for account " + account.getUsername();

        String content = "Hi " + account.getDisplayName() + ",\n\n";
        content += "We have received a request to reset the password for your account " + account.getUsername() + "\n";
        content += "To reset your password, use the code below:\n\n";
        content += code;
        content += "\n\nThank you for shopping with PMarket! \n\nSent at: " + LocalDateTime.now();

        Email.sendMail(senderName, toAddress, subject, content);
    }

    public static void sendPasswordResetRequest(String emailAddress, String username, String displayName, String code) {
        String senderName = "PMarket";
        String toAddress = emailAddress;

        String subject = "Password reset request for account " + username;

        String content = "Hi " + displayName + ",\n\n";
        content += "We have received a request to reset the password for your account " + username + "\n";
        content += "To reset your password, use the code below:\n\n";
        content += code;
        content += "\n\nThank you for shopping with PMarket! \n\nSent at: " + LocalDateTime.now();

        Email.sendMail(senderName, toAddress, subject, content);
    }
    
    public static void sendPasswordResetConfirmation(String emailAddress, String username, String displayName, String code) {
        String senderName = "PMarket";
        String toAddress = emailAddress;

        String subject = "Recent password reset for account " + username;

        String content = "Hi " + displayName + ",\n\n";
        content += "The password for your account " + username + " has recently been updated.\n\n";
        content += "Thank you for shopping with PMarket! \n\nSent at: " + LocalDateTime.now();

        Email.sendMail(senderName, toAddress, subject, content);
    }
}
