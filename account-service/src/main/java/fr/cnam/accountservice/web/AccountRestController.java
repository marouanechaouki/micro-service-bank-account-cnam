package fr.cnam.accountservice.web;

import fr.cnam.accountservice.clients.CustomerRestClient;
import fr.cnam.accountservice.entities.BankAccount;
import fr.cnam.accountservice.model.Customer;
import fr.cnam.accountservice.repositories.BankAccountRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountRestController {
    private BankAccountRepository bankrepository;
    private CustomerRestClient customerrestclient;
    public AccountRestController(BankAccountRepository bankrepository, CustomerRestClient customerrestclient) {
        this.bankrepository = bankrepository;
        this.customerrestclient = customerrestclient;
    }


    @GetMapping("/accounts/{id}")
    @CircuitBreaker(name="customerService",fallbackMethod = "getDefaultCustomer")
    public BankAccount findBankAccountById(@PathVariable String id) {
        BankAccount bankAccount = bankrepository.findById(id).orElseThrow(()-> new RuntimeException("Bank account not found"));
        Customer customer = customerrestclient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }

    @GetMapping("/accounts")
    @CircuitBreaker(name="customerService",fallbackMethod = "getDefaultListCustomers")
    public List<BankAccount> accountList() {
        List<BankAccount> bankAccountList =  bankrepository.findAll();
        bankAccountList.forEach(bankAccount -> {
            bankAccount.setCustomer(customerrestclient.findCustomerById(bankAccount.getCustomerId()));
        });
        return bankAccountList;
    }


    Customer getDefaultCustomer(Long id, Exception exception){
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName("Not available");
        customer.setLastName("Not available");
        customer.setEmail("Not available");
        return customer;
    }

    List<BankAccount> getDefaultListCustomers (Throwable exception){
        return List.of(); // liste vide
    }

}
