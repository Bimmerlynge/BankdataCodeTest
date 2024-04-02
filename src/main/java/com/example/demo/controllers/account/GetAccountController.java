package com.example.demo.controllers.account;

import com.example.demo.application.IAccountService;
import com.example.demo.domain.Account;
import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.application.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GetAccountController {
    private final IAccountService accountService;

    public GetAccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts/{id}")
    @Tag(name = "Accounts")
    public ResponseEntity<?> Get(@PathVariable long id) throws AccountNotFoundException {
        Account account = accountService.GetAccount(id);
        return ResponseEntity.ok(account);
    }
}
