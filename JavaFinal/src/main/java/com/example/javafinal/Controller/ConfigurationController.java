package com.example.javafinal.Controller;

import com.example.javafinal.Entities.Configuration;
import com.example.javafinal.Model.Request.SaveConfigurationRequest;
import com.example.javafinal.Repository.ConfigurationRepository;
import com.example.javafinal.Service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/configuration")
public class ConfigurationController {
    private ConfigurationRepository configurationRepository;

    @Autowired
    public ConfigurationController(ConfigurationRepository configurationRepository
    ) {
        this.configurationRepository = configurationRepository;
    }


    @GetMapping(value = "/")
    public Configuration getConfiguration() {
        ConfigurationService service = new ConfigurationService(configurationRepository);
        return service.getConfiguration();

    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> SaveConfiguration(@RequestBody SaveConfigurationRequest request) {
        ConfigurationService service = new ConfigurationService(configurationRepository);
        service.saveConfiguration(request);
        return ResponseEntity.ok("SAVED_CONFIGURATION");
    }


}