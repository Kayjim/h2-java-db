package com.tts.h2example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tts.h2example.domain.Customer;
import com.tts.h2example.domain.CustomerRepository;

@SpringBootApplication
public class H2exampleApplication {
    private static final Logger log = LoggerFactory.getLogger(H2exampleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(H2exampleApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository repository) {
        return args -> {
            //save a couple customers
            repository.save( new Customer("Jimmy", "Dang"));
            repository.save(new Customer("Michael", "Smith"));
            repository.save(new Customer("Aaron", "Moon"));
            repository.save(new Customer("Kim", "Lassiter"));
            repository.save(new Customer("Joan", "Daniels"));
            repository.save(new Customer("Eric", "Patterson"));

            //read all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // read an individual customer by ID
            repository.findById(1L)
                    .ifPresent(customer -> {
                        log.info("Customer found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(customer.toString());
                        log.info("");
                    });

            // read customers by last name
            log.info("Customer found with findByLastName('Patterson'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Patterson").forEach(bauer -> {
                log.info(bauer.toString());
            });

            log.info("");
        };
    }

}

