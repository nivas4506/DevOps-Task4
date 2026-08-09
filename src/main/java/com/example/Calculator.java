package com.example;

public class Calculator {

    // Code Smell / Maintainability: Magic numbers & duplicate literal
    public double calculateDiscount(double price, int customerYears, String type) {
        double discount = 0.0;

        if (type == "REGULAR") { // Bug: String comparison using '=='
            if (customerYears > 5) {
                discount = price * 0.05; // Magic number
            } else {
                discount = price * 0.02; // Magic number
            }
        } else if (type == "VIP") { // Bug: String comparison using '=='
            if (customerYears > 5) {
                discount = price * 0.20; // Magic number
            } else {
                discount = price * 0.10; // Magic number
            }
        } else {
            discount = 0.0;
        }

        // Redundant / unreachable condition
        if (discount < 0) {
            return 0.0;
        }

        return discount;
    }

    // Bug: Division by Zero risk when count is 0
    public int average(int total, int count) {
        return total / count; // Will throw ArithmeticException if count == 0
    }

    public int add(int a, int b) {
        return a + b;
    }
}
