package demo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class App {
    // Generate a random salt
    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    // Hash a password using SHA-256 and a salt
    public static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes());

        // Convert the byte array to a hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashedPassword) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Validate a password by comparing its hash with a stored hash
    public static boolean validatePassword(String password, String storedHash, byte[] salt) throws NoSuchAlgorithmException {
        String hashedPassword = hashPassword(password, salt);
        return storedHash.equals(hashedPassword);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Example usage:

        String password = "myPassword";
        byte[] salt = generateSalt();
        String hashedPassword = hashPassword(password, salt);

        // Print the salt and hashed password
        System.out.println("Salt: " + new String(salt));
        System.out.println("Hashed Password: " + hashedPassword);

        // To validate a password during login
        boolean isPasswordValid = validatePassword("myPassword", hashedPassword, salt);
        System.out.println("Is Password Valid? " + isPasswordValid);
    }
}
