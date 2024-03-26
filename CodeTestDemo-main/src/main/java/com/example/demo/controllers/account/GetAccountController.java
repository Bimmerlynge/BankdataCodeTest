package com.example.demo.controllers.account;

import com.example.demo.domain.Account;
import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.infrastructure.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class GetAccountController {
    private final AccountService _accountService;

    public GetAccountController(AccountService accountService) {
        _accountService = accountService;
    }

    @GetMapping("/account")
    public ResponseEntity<?> Get(@RequestParam long id) throws AccountNotFoundException {
        Account account = _accountService.GetAccount(id);
        return ResponseEntity.ok(account);
    }
}
