package com.example.javafinal.Service;

import com.example.javafinal.Entities.Configuration;
import com.example.javafinal.Model.Request.SaveConfigurationRequest;
import com.example.javafinal.Repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {

    private ConfigurationRepository configurationRepository;

    @Autowired
    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public Configuration getConfiguration() {
        Configuration entity = configurationRepository.findById(Long.valueOf(1)).orElse(null);
        return entity;
    }

    public void saveConfiguration(SaveConfigurationRequest request) {
        Configuration entity = Configuration.builder()
                .totalTickets(request.getTotalTickets())
                .customerRetrievalRate(request.getCustomerRetrievalRate())
                .maxCapacity(request.getMaxCapacity())
                .eventName(request.getEventName())
                .ticketReleaseRate(request.getTicketReleaseRate()).build();
        configurationRepository.save(entity);
    }
}