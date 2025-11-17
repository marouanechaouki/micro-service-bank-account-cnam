package fr.cnam.accountservice.model;

import fr.cnam.accountservice.entities.BankAccount;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
