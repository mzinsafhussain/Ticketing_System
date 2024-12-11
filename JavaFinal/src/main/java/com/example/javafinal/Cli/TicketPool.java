package com.example.javafinal.Cli;
import java.util.*;
import java.util.concurrent.locks.*;

class TicketPool {
    private final Queue<String> tickets = new LinkedList<>();
    private final int maxCapacity;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void addTicket(String ticket) throws InterruptedException {
        lock.lock();
        try {
            while (tickets.size() == maxCapacity) {
                System.out.println("Ticket Pool is full. Vendor is waiting...");
                notFull.await(); // Wait until there's space in the pool
            }
            tickets.add(ticket);
            System.out.println("Ticket Added: " + ticket);
            notEmpty.signal(); // Notify customers that a ticket is available
        } finally {
            lock.unlock();
        }
    }

    public String retrieveTicket() throws InterruptedException {
        lock.lock();
        try {
            while (tickets.isEmpty()) {
                System.out.println("Ticket Pool is empty. Customer is waiting...");
                notEmpty.await(); // Wait until there's a ticket available
            }
            String ticket = tickets.poll();
            System.out.println("Ticket Retrieved: " + ticket);
            notFull.signal(); // Notify vendors that space is available
            return ticket;
        } finally {
            lock.unlock();
        }
    }

    public int getAvailableTickets() {
        lock.lock();
        try {
            return tickets.size();
        } finally {
            lock.unlock();
        }
    }

    public void displayTickets() {
        lock.lock();
        try {
            System.out.println("Current Tickets in Pool: " + tickets);
        } finally {
            lock.unlock();
        }
    }
}
