package cli;

import decorator.RibbonDecorator;
import Factory.FlowerFactory;
import model.Flower;
import payment.CreditCardStrategy;
import payment.PayPalStrategy;
import payment.PaymentStrategy;
import singleton.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static singleton.DatabaseConnection.getInstance;

public class FlowerOrderingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcome Message
        System.out.println("Welcome to the Flower Ordering System!");

        // Ask for Flower Type
        System.out.println("What type of flower would you like to order? (Rose/Tulip)");
        String flowerType = scanner.nextLine();
        Flower flower = new FlowerFactory().getFlower(flowerType.toUpperCase());

        // Decorator Option
        System.out.println("Would you like to add a ribbon? (yes/no)");
        String ribbonChoice = scanner.nextLine();
        if ("yes".equalsIgnoreCase(ribbonChoice)) {
            flower = new RibbonDecorator(flower);
        }

        // Personal Details
        System.out.println("Please enter the recipient's name:");
        String name = scanner.nextLine();

        System.out.println("Please enter the delivery address:");
        String address = scanner.nextLine();

        System.out.println("Any special message to include with the flowers?");
        String message = scanner.nextLine();

        // Contact Number
        System.out.println("Please provide a contact number for delivery:");
        String contactNumber = scanner.nextLine();

        // Payment and Price (Assuming fixed price for simplicity)
        System.out.println("The total price is $20. How would you like to pay? (CreditCard/PayPal)");
        String paymentMethod = scanner.nextLine();
        PaymentStrategy paymentStrategy = paymentMethod.equalsIgnoreCase("PayPal") ?
                new PayPalStrategy() : new CreditCardStrategy();

        // Processing Payment
        paymentStrategy.pay("20");

        // Order Confirmation
        System.out.println("Thank you for your order!");
        System.out.println("Order Details:");
        flower.displayInfo();
        System.out.println("To: " + name);
        System.out.println("Address: " + address);
        System.out.println("Message: " + message);
        System.out.println("Contact Number: " + contactNumber);

        // Insert Order Details into PostgreSQL Database
        try {
            var dbConnection = getInstance();
            Connection connection = dbConnection.getConnection();

            // Create SQL INSERT statement
            String insertSQL = "INSERT INTO flower_orders (flower_type, ribbon_added, recipient_name, delivery_address, special_message, contact_number, payment_method) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

            // Set values based on user input
            preparedStatement.setString(1, flowerType);
            preparedStatement.setBoolean(2, "yes".equalsIgnoreCase(ribbonChoice));
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, message);
            preparedStatement.setString(6, contactNumber);
            preparedStatement.setString(7, paymentMethod);

            // Execute the INSERT statement
            preparedStatement.executeUpdate();

            // Commit the transaction
            connection.commit();

            // Close the statement
            preparedStatement.close();

            System.out.println("Order details saved to the database successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database-related exceptions
        }

        scanner.close();
    }
}
