package br.com.alvesmateus.controleFinanceiro.security.auth.dtos;

public record RegisterRequest(
    String name, 
    String email, 
    String password
) {}
