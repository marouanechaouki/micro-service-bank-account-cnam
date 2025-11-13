package fr.cnam.customerservice;

import fr.cnam.customerservice.config.GlobalConfig;
import fr.cnam.customerservice.entities.Customer;
import fr.cnam.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> Stream.of("Marouane", "Lamiaa", "Rim").forEach(name -> {
            Customer customer = Customer.builder()
                    .firstName(name)
                    .lastName(Math.random()>0.5?"Chaouki":"Salam")
                    .email(name+"@gmail.com")
                    .build();
                    customerRepository.save(customer);
        });
    }
}
