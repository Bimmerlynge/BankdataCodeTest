package com.example.demo.controllers.account;

import com.example.demo.application.AccountService;
import com.example.demo.domain.Account;
import com.example.demo.exceptions.AccountNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PutNewBalance {
    private final AccountService _accountService;

    public PutNewBalance(AccountService accountService) {
        _accountService = accountService;
    }


    @PutMapping("/accounts/{id}/deposit")
    public ResponseEntity<?> Put(@PathVariable long id, @RequestParam double amount) throws AccountNotFoundException {
        Account account = _accountService.Deposit(id, amount);
        return ResponseEntity.ok(account);
    }
}
