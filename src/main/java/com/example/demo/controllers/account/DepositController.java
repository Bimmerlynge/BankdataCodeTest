package com.example.demo.controllers.account;

import com.example.demo.application.AccountService;
import com.example.demo.application.IAccountService;
import com.example.demo.domain.Account;
import com.example.demo.exceptions.AccountNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepositController {
    private final IAccountService accountService;

    public DepositController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PutMapping("/accounts/{id}/deposit")
    @Tag(name = "Accounts")
    public ResponseEntity<?> Put(@PathVariable long id, @RequestParam double amount) throws AccountNotFoundException {
        Account account = accountService.Deposit(id, amount);
        return ResponseEntity.ok(account);
    }
}
