package fr.cnam.customerservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class BankAccount {
    private String accountId;
    private double solde;
    private LocalDate CreatedAt;
    private String currency;
    private String type;

}
