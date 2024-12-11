package com.example.javafinal.Cli;
import java.util.Scanner;

class SystemConfigurations {
    private int totalTickets;
    private int maxCapacity;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private String eventName;

    public void configureSystem(Scanner scanner) {
        System.out.println("~~~WELCOME TO TICKETING SYSTEM~~~");

        this.totalTickets = getValidInput(scanner, "Enter The Total Number Of Tickets: ");
        this.maxCapacity = getValidInput(scanner, "Enter The Maximum Ticket Capacity: ");
        this.ticketReleaseRate = getValidInput(scanner, "Enter The Ticket Release Rate (In Milliseconds): ");
        this.customerRetrievalRate = getValidInput(scanner, "Enter The Customer Retrieval Rate (In Milliseconds): ");
        System.out.print("Enter The Event Name: ");
        this.eventName = scanner.next();
        System.out.println("Configuration Complete. Thank you\n");
    }

    public void displayConfiguration() {
        System.out.println("===== Current Configuration =====");
        System.out.println("Total Tickets: " + totalTickets);
        System.out.println("Max Pool Capacity: " + maxCapacity);
        System.out.println("Ticket Release Rate: " + ticketReleaseRate + " ms");
        System.out.println("Customer Retrieval Rate: " + customerRetrievalRate + " ms");
        System.out.println("Event Name: " + eventName);
        System.out.println();
    }

    private int getValidInput(Scanner scanner, String prompt) {
        int input;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= 0) {
                    break;
                } else {
                    System.out.println("Please enter a non-negative number.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            }
        }
        return input;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public String getEventName() {
        return eventName;
    }
}
