package com.example.javafinal;

import com.example.javafinal.Cli.TicketingSystemCLI;
import com.sun.tools.javac.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaFinalApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JavaFinalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        TicketingSystemCLI.main(args);
    }
}
