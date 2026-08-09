package com.example;

public class App {
    private static final String APP_VERSION = "1.0.0";

    public static void main(String[] args) {
        System.out.println("Starting Java SonarQube Demo Application v" + APP_VERSION);

        UserService userService = new UserService();
        
        String role = userService.getUserRole("admin");
        System.out.println("User Role: " + role);

        String adminPass = System.getenv().getOrDefault("ADMIN_PASSWORD", "");
        boolean isAuthenticated = userService.authenticateUser("admin", adminPass);
        System.out.println("Authenticated: " + isAuthenticated);

        userService.processUserBatch(100, "active", true, false, 50, "US_EAST");

        Calculator calculator = new Calculator();
        double result = calculator.calculateDiscount(500.0, 15, "VIP");
        System.out.println("Calculated Discount: " + result);

        int count = 5;
        if (count > 0) {
            int avg = calculator.average(100, count);
            System.out.println("Average: " + avg);
        }
    }
}
