package br.com.alvesmateus.controleFinanceiro.bankAccount;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID idUser;

    private String name;

    private Long openingBalance;

    @Enumerated(EnumType.STRING)
    private BankAccountType bankAccountType;
}
