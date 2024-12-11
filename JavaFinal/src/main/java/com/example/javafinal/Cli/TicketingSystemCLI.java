package com.example.javafinal.Cli;
import java.util.*;
import java.util.concurrent.*;

public class TicketingSystemCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Configure the system
        SystemConfigurations config = new SystemConfigurations();
        config.configureSystem(scanner);

        TicketPool ticketPool = new TicketPool(config.getMaxCapacity());
        double ticketPrice = 20.0; // Example ticket price

        // Step 2: Setup vendors and customers
        int numVendors = 2; // Fixed number of vendors
        int numCustomers = 2; // Fixed number of customers

        ExecutorService executorService = Executors.newFixedThreadPool(numVendors + numCustomers);

        // Store references to threads
        List<Vendor> vendors = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();

        // Start vendors
        for (int i = 1; i <= numVendors; i++) {
            Vendor vendor = new Vendor(ticketPool, config.getTicketReleaseRate(), "Vendor" + i);
            vendors.add(vendor);
            executorService.submit(vendor);
        }

        // Start customers
        for (int i = 1; i <= numCustomers; i++) {
            Customer customer = new Customer(ticketPool, config.getCustomerRetrievalRate(), "Customer" + i);
            customers.add(customer);
            executorService.submit(customer);
        }

        // Monitor the system
        System.out.println("Commands: start, stop");
        String command = scanner.nextLine();
        if (command.equalsIgnoreCase("start")) {
            System.out.println("Starting ticketing system...");
            while (true) {
                command = scanner.nextLine();
                if (command.equalsIgnoreCase("stop")) {
                    break;
                }
                System.out.println("Invalid command. Type 'stop' to exit.");
            }
        }

        // Stop vendors and customers
        System.out.println("Stopping ticketing system...");
        vendors.forEach(Vendor::stop);
        customers.forEach(Customer::stop);
        executorService.shutdownNow();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                System.err.println("Timeout reached while stopping threads.");
            }
        } catch (InterruptedException e) {
            System.err.println("Error stopping threads: " + e.getMessage());
        }
        System.out.println("System stopped successfully.");
    }
}
