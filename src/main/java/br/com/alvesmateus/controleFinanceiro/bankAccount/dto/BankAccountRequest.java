package br.com.alvesmateus.controleFinanceiro.bankAccount.dto;

import java.math.BigDecimal;

import br.com.alvesmateus.controleFinanceiro.bankAccount.BankAccountType;

public record BankAccountRequest(
    String name,
    BigDecimal openingBalance,
    BankAccountType accountType
) {}
