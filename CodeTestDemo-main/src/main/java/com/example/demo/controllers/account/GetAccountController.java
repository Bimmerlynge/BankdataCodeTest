package com.example.demo.controllers.account;

import com.example.demo.domain.Account;
import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.application.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GetAccountController {
    private final AccountService _accountService;

    public GetAccountController(AccountService accountService) {
        _accountService = accountService;
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<?> Get(@PathVariable long id) throws AccountNotFoundException {
        Account account = _accountService.GetAccount(id);
        return ResponseEntity.ok(account);
    }
}