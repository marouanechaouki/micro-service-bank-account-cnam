package fr.cnam.customerservice.web;


import fr.cnam.customerservice.entities.Customer;
import fr.cnam.customerservice.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerRestController {

    private CustomerRepository customerRepository;
    
    @GetMapping("/customers")
    public List<Customer> customerList() {
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer customerById(@PathVariable  Long id) {
        return customerRepository.findById(id).get();
    }
}
