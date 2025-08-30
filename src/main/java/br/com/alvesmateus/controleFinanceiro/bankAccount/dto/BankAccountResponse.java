package br.com.alvesmateus.controleFinanceiro.bankAccount.dto;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.alvesmateus.controleFinanceiro.bankAccount.BankAccountType;
import br.com.alvesmateus.controleFinanceiro.user.dtos.UserResponse;

public record BankAccountResponse(
    UUID idBankAccount,
    UserResponse user,
    String name,
    BigDecimal openingBalance,
    BankAccountType accountType
) {}
