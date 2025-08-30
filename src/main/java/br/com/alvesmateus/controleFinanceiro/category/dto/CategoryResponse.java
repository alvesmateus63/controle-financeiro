package br.com.alvesmateus.controleFinanceiro.category.dto;

import br.com.alvesmateus.controleFinanceiro.user.dtos.UserResponse;

public record CategoryResponse(
    Long id,
    String name,
    UserResponse user
) {}
