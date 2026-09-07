package com.bankdki.jakone_be.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankdki.jakone_be.dto.RegisterRequest;
import com.bankdki.jakone_be.dto.TransactionRequest;
import com.bankdki.jakone_be.dto.TransactionResponse;
import com.bankdki.jakone_be.entity.Account;
import com.bankdki.jakone_be.entity.Mutation;
import com.bankdki.jakone_be.service.AccountService;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> register(@RequestBody RegisterRequest request) {
        Account createdAccount = accountService.registerAccount(request);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
        Account account = accountService.getAccountByNumber(accountNumber);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/{accountNumber}/transact")
    public ResponseEntity<TransactionResponse> transact(
            @PathVariable String accountNumber,
            @RequestBody TransactionRequest request) {
        TransactionResponse response = accountService.processTransaction(accountNumber, request);
        return ResponseEntity.ok(response);
    }

    // Mutation History Ledger
    @GetMapping("/{accountNumber}/mutations")
    public ResponseEntity<List<Mutation>> getMutations(@PathVariable String accountNumber) {
        List<Mutation> mutations = accountService.getAccountMutations(accountNumber);
        return ResponseEntity.ok(mutations);
    }
}