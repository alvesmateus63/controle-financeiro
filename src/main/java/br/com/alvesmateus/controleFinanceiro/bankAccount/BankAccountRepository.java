package br.com.alvesmateus.controleFinanceiro.bankAccount;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alvesmateus.controleFinanceiro.user.User;

public interface BankAccountRepository extends JpaRepository<BankAccount, UUID>  {

    List<BankAccount> findByUser(User user);

    Optional<BankAccount> findByIdAndUserId(UUID id, UUID user);

}
