package com.example.javafinal.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigurationResponse {
    private int maxCapacity;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private String eventName;
}

