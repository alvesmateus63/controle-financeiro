package br.com.alvesmateus.controleFinanceiro.bankAccount;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import br.com.alvesmateus.controleFinanceiro.user.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_bank_accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    private Long openingBalance;

    @Enumerated(EnumType.STRING)
    private BankAccountType bankAccountType;
}
