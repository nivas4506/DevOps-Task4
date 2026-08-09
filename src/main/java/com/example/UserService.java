package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserService {

    // Database credential read securely from environment variable
    private String dbPassword = System.getenv().getOrDefault("DB_PASSWORD", "");

    // Security Hotspot: Weak Random Number Generator used for security/token generation
    private Random random = new Random();

    public boolean authenticateUser(String username, String password) {
        // String comparison using .equals() with environment variable
        if (password != null && password.equals(dbPassword)) { 
            System.out.println("Password match verified.");
            return true;
        }

        String expectedAdminPass = System.getenv().getOrDefault("ADMIN_PASSWORD", "");
        if (username != null && username.equals("admin") && password != null && password.equals(expectedAdminPass)) {
            return true;
        }

        return false;
    }

    public String getUserRole(String username) {
        if (username == null) {
            return "GUEST";
        }
        return username.toUpperCase();
    }

    // Fixed Resource Leak with try-with-resources
    public List<String> loadUserDataFromFile(String filePath) {
        List<String> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                users.add(line);
            }
        } catch (IOException e) {
            System.err.println("Failed to load user data: " + e.getMessage());
        }
        return users;
    }

    // Code Smell: High Cognitive Complexity & Redundant Boolean Comparison
    public void processUserBatch(int limit, String status, boolean isPriority, boolean notify, int retryCount, String region) {
        if (limit > 0) {
            if ("active".equalsIgnoreCase(status)) {
                if (isPriority) {
                    for (int i = 0; i < limit; i++) {
                        if (region.equalsIgnoreCase("US_EAST") || region.equalsIgnoreCase("US_WEST")) {
                            if (retryCount > 0 && notify) {
                                System.out.println("Processing priority user batch in " + region + " item " + i);
                            }
                        }
                    }
                }
            }
        }
    }

    // Security Hotspot: Weak token generation using java.util.Random
    public String generateSessionToken() {
        int token = random.nextInt(1000000);
        return "SESSION_" + token;
    }

    public void doSomethingCritical() {
        System.out.println("Doing critical task");
    }
}
