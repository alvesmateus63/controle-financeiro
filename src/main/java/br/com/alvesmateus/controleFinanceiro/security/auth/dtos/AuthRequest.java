package br.com.alvesmateus.controleFinanceiro.security.auth.dtos;

public record AuthRequest(
    String email, 
    String password
) {}
