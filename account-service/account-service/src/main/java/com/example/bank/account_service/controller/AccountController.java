package com.example.bank.account_service.controller;

import com.example.bank.account_service.dto.AccountDtoResponse;
import com.example.bank.account_service.entity.Account;
import com.example.bank.account_service.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/{customerId}")
    public ResponseEntity<Account> createAccount(
            @PathVariable Long customerId,
            @RequestBody Account account) {

        return ResponseEntity.ok(accountService.createAccount(customerId, account));
    }


    // CREATE
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account saved = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Account>> getAccount(@PathVariable Long id) {
        Optional<Account> acc = accountService.findByAccountId(id);
        return ResponseEntity.ok(acc);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Account>> getAll() {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(
            @RequestBody Account account,
            @PathVariable Long id) {

        Account updated = accountService.updateAccount(account, id);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteById(id);
        return ResponseEntity.ok("Account deleted successfully");
    }

    @GetMapping("/customer/{customerId}")
    public List<AccountDtoResponse> getByCustomerId(@PathVariable Long customerId) {
        return accountService.getAccountDtosByCustomerId(customerId);
    }


    @PutMapping("/{id}/balance/add")
    public ResponseEntity<Void> addBalance(@PathVariable Long id, @RequestParam Double amount) {
        accountService.addBalance(id, amount);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/balance/subtract")
    public ResponseEntity<Void> subtractBalance(@PathVariable Long id, @RequestParam Double amount) {
        accountService.subBalance(id, amount);
        return ResponseEntity.ok().build();
    }


}
