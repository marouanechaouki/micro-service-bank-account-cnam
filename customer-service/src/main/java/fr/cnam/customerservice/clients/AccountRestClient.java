package fr.cnam.customerservice.clients;

import fr.cnam.customerservice.entities.Customer;
import fr.cnam.customerservice.model.BankAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ACCOUNT-SERVICE")
public interface AccountRestClient {

    @GetMapping("/customers/{id}/details")
    List<BankAccount> findAccountsByCustomer(@PathVariable Long id);
}
