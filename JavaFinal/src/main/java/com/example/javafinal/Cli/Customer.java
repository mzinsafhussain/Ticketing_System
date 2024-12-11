package com.example.javafinal.Cli;
public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int retrievalInterval;
    private final String customerId;
    private volatile boolean running = true; // Termination flag

    public Customer(TicketPool ticketPool, int retrievalInterval, String customerId) {
        this.ticketPool = ticketPool;
        this.retrievalInterval = retrievalInterval;
        this.customerId = customerId;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                // Try to retrieve a ticket from the ticket pool
                String ticket = ticketPool.retrieveTicket();
                if (ticket != null) {
                    System.out.println(customerId + " purchased " + ticket);
                }
                // Simulate delay in customer retrieval
                Thread.sleep(retrievalInterval);
            } catch (InterruptedException e) {
                // Handle interruption gracefully
                System.out.println("Customer " + customerId + " interrupted.");
                running = false; // Exit the loop
            }
        }
    }
}
