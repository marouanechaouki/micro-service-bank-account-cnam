package fr.cnam.customerservice.web;


import fr.cnam.customerservice.clients.AccountRestClient;
import fr.cnam.customerservice.entities.Customer;
import fr.cnam.customerservice.model.BankAccount;
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
    private AccountRestClient accountRestClient;
    
    @GetMapping("/customers")
    public List<Customer> customerList() {
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer customerById(@PathVariable  Long id) {
        Customer customer = customerRepository.findById(id).get();
        List<BankAccount> bankAccountList = accountRestClient.findAccountsByCustomer(id);
        customer.setBankAccountList(bankAccountList);
        return customer;
    }
}
