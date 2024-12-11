package com.example.javafinal.Cli;
class Ticket {
    private final String ticketId;
    private final String eventName;
    private final double ticketPrice;

    public Ticket(String ticketId, String eventName, double ticketPrice) {
        this.ticketId = ticketId;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Ticket{ticketId='" + ticketId + "', eventName='" + eventName + "', ticketPrice=" + ticketPrice + "}";
    }
}
