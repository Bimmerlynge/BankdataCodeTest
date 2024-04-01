package com.example.demo.controllers.account;

import com.example.demo.domain.Account;
import com.example.demo.application.AccountService;
import com.example.demo.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateAccountController {
    private final AccountService _accountService;

    @Autowired
    public CreateAccountController(AccountService accountService) {
        _accountService = accountService;
    }

    @PostMapping("/accounts")
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest accountRequest) {
        if (!accountRequest.IsValid()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request object");
        }

        String firstname = accountRequest.getFirstname();
        String lastname = accountRequest.getFirstname();
        double balance = accountRequest.getBalance();

        Account newAccount = _accountService.CreateUser(firstname, lastname, balance);

        return ResponseEntity.ok(newAccount);
    }
}

class AccountRequest{
    private String firstname;
    private String lastname;
    private double balance;

    public AccountRequest() {
    }

    public AccountRequest(String firstname, String lastname, double balance) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.balance = balance;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean IsValid(){
        return !ValidationUtils.StringEmptyOrNull(firstname) &&
                !ValidationUtils.StringEmptyOrNull(lastname) &&
                !Double.isNaN(balance);
    }
}



