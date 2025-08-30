package br.com.alvesmateus.controleFinanceiro.bankAccount;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alvesmateus.controleFinanceiro.bankAccount.dto.BankAccountRequest;
import br.com.alvesmateus.controleFinanceiro.bankAccount.dto.BankAccountResponse;
import br.com.alvesmateus.controleFinanceiro.bankAccount.dto.BankAccountUpdateRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bankAccount")
@RequiredArgsConstructor
public class BankAccountController {

    @Autowired
    private final BankAccountService bankAccountService;

    @GetMapping
    public ResponseEntity<List<BankAccountResponse>> getAllBankAccounts() {
        List<BankAccountResponse> response = bankAccountService.getAllBankAccounts();

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<BankAccountResponse> getBankAccount(@PathVariable UUID id) {
        BankAccountResponse response = bankAccountService.getBankAccount(id);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BankAccountResponse> createBankAccount(@RequestBody BankAccountRequest request) {
        BankAccountResponse response = bankAccountService.createBankAccount(request);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccountResponse> updateBankAccount(
        @PathVariable UUID id, 
        @RequestBody BankAccountUpdateRequest request) {

        BankAccountResponse response = bankAccountService.updateBankAccount(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable UUID id){
        bankAccountService.deleteById(id);
        
        return ResponseEntity.noContent().build();
    }

}
