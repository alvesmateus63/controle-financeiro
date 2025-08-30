package br.com.alvesmateus.controleFinanceiro.security.auth.dto;

public record AuthRequest(
    String email, 
    String password
) {}
