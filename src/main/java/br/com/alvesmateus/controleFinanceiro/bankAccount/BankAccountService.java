package br.com.alvesmateus.controleFinanceiro.bankAccount;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.alvesmateus.controleFinanceiro.bankAccount.dto.BankAccountRequest;
import br.com.alvesmateus.controleFinanceiro.bankAccount.dto.BankAccountResponse;
import br.com.alvesmateus.controleFinanceiro.bankAccount.dto.BankAccountUpdateRequest;
import br.com.alvesmateus.controleFinanceiro.core.utils.CurrencyUtils;
import br.com.alvesmateus.controleFinanceiro.user.User;
import br.com.alvesmateus.controleFinanceiro.user.UserService;
import br.com.alvesmateus.controleFinanceiro.user.dtos.UserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserService userService;

    public List<BankAccountResponse> getAllBankAccounts() {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<BankAccount> bankAccounts = bankAccountRepository.findByUser(loggedUser);

        return bankAccounts.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public BankAccountResponse getBankAccount(UUID id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow();

        return toResponseDTO(bankAccount);
    }

    @Transactional
    public BankAccountResponse createBankAccount(BankAccountRequest request) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        BankAccount newBankAccount = new BankAccount();
        newBankAccount.setUser(loggedUser);
        newBankAccount.setName(request.name());
        newBankAccount.setOpeningBalance(CurrencyUtils.toCents(request.openingBalance()));
        newBankAccount.setBankAccountType(request.accountType());


        BankAccount savedBankAccount = bankAccountRepository.save(newBankAccount);

        return toResponseDTO(savedBankAccount);
    }

    @Transactional
    public BankAccountResponse updateBankAccount(UUID id, BankAccountUpdateRequest request) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var existingBankAccount = bankAccountRepository
            .findByIdAndUserId(id, loggedUser.getId())
            .orElseThrow(() -> new RuntimeException("Bank Account not found"));

        existingBankAccount.setName(request.name());
        existingBankAccount.setBankAccountType(request.accountType());

        return toResponseDTO(existingBankAccount);
    }

    @Transactional
    public void deleteById(UUID id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var bankAccountToDelete = bankAccountRepository
            .findByIdAndUserId(id, loggedUser.getId())
            .orElseThrow(() -> new RuntimeException("Bank Account not found"));


        bankAccountRepository.delete(bankAccountToDelete);
    }

    private BankAccountResponse toResponseDTO(BankAccount bankAccount) {

        UserResponse userResponse = userService.toResponseDTO(bankAccount.getUser());

        BigDecimal openingBalanceDecimal = CurrencyUtils.fromCents(bankAccount.getOpeningBalance());

        return new BankAccountResponse(
            bankAccount.getId(),
            userResponse,
            bankAccount.getName(),
            openingBalanceDecimal,
            bankAccount.getBankAccountType()
        );
    }

}
