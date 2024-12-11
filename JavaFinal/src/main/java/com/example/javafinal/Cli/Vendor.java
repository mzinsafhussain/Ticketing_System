package com.example.javafinal.Cli;
public class Vendor implements Runnable {
    private final TicketPool pool;
    private final int interval;
    private final String id;
    private volatile boolean running = true; // Termination flag

    public Vendor(TicketPool pool, int interval, String id) {
        this.pool = pool;
        this.interval = interval;
        this.id = id;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        try {
            int ticketNumber = 1;
            while (running) {
                String ticket = "Ticket-" + id + "-" + ticketNumber++;
                pool.addTicket(ticket);
                System.out.println(id + " has placed " + ticket + " in the pool.");
                Thread.sleep(interval); // Simulates delay in ticket release
            }
        } catch (InterruptedException ex) {
            System.out.println("Vendor " + id + " process halted.");
        }
    }
}
