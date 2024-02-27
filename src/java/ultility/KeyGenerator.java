package ultility;

import java.util.Random;

public class KeyGenerator {

    private static String generateString(int length) {
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";

        String alphaNumeric = upperAlphabet + numbers;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphaNumeric.length());
            char randomChar = alphaNumeric.charAt(index);
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString;
    }
    
    public static String generateKey() {
        String key = generateString(5) + "-" + generateString(5) + "-" + generateString(5);
        return key;
    }
}
