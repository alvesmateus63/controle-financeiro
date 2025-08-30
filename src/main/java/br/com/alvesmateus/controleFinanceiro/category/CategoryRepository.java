package br.com.alvesmateus.controleFinanceiro.category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alvesmateus.controleFinanceiro.user.User;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUser(User user);

    Optional<Category> findByIdAndUserId(Long id, UUID user);
}
