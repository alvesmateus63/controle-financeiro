package br.com.alvesmateus.controleFinanceiro.bankAccount.dto;

import br.com.alvesmateus.controleFinanceiro.bankAccount.BankAccountType;

public record BankAccountUpdateRequest(
    String name,
    BankAccountType accountType
) {}
