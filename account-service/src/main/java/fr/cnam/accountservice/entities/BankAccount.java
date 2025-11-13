package fr.cnam.accountservice.entities;

import fr.cnam.accountservice.enums.AccountType;
import fr.cnam.accountservice.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter @NoArgsConstructor @AllArgsConstructor @ToString @Builder
public class BankAccount {
    @Id
    private String accountId;
    private double solde;
    private LocalDate CreatedAt;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Transient
    private Customer customer;
    private Long customerId;


}
