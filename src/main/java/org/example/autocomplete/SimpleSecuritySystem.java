package org.example.autocomplete;

import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SimpleSecuritySystem {
    private static final Map<String, String> users = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.print("Enter username: ");
                String registerUsername = scanner.nextLine();
                System.out.print("Enter password: ");
                String registerPassword = scanner.nextLine();
                registerUser(registerUsername, registerPassword);
            } else if (option == 2) {
                System.out.print("Enter username: ");
                String loginUsername = scanner.nextLine();
                System.out.print("Enter password: ");
                String loginPassword = scanner.nextLine();
                loginUser(loginUsername, loginPassword);
            } else if (option == 3) {
                running = false;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }

    private static void registerUser(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Choose a different one.");
            return;
        }
        String hashedPassword = hashPassword(password);
        users.put(username, hashedPassword);
        System.out.println("User registered successfully.");
    }

    private static void loginUser(String username, String password) {
        String hashedPassword = users.get(username);
        if (hashedPassword != null && hashedPassword.equals(hashPassword(password))) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}