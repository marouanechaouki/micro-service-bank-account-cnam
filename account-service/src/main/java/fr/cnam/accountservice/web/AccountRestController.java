package fr.cnam.accountservice.web;

import fr.cnam.accountservice.clients.CustomerRestClient;
import fr.cnam.accountservice.entities.BankAccount;
import fr.cnam.accountservice.model.Customer;
import fr.cnam.accountservice.repositories.BankAccountRepository;
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
    public BankAccount findBankAccountById(@PathVariable String id) {
        BankAccount bankAccount = bankrepository.findById(id).orElseThrow(()-> new RuntimeException("Bank account not found"));
        Customer customer = customerrestclient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }

    @GetMapping("/accounts")
    public List<BankAccount> accountList() {
        List<BankAccount> bankAccountList =  bankrepository.findAll();
        bankAccountList.forEach(bankAccount -> {
            bankAccount.setCustomer(customerrestclient.findCustomerById(bankAccount.getCustomerId()));
        });
        return bankAccountList;
    }


}
