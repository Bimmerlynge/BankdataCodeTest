package com.example.demo.controllers.account;

import com.example.demo.application.IAccountService;
import com.example.demo.domain.Account;
import com.example.demo.application.AccountService;
import com.example.demo.util.ValidationUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateAccountController {
    private final IAccountService accountService;

    @Autowired
    public CreateAccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/accounts")
    @Tag(name = "Accounts")
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest accountRequest) {
        if (!accountRequest.IsValid()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request object");
        }

        String firstname = accountRequest.getFirstname();
        String lastname = accountRequest.getLastname();
        double balance = accountRequest.getBalance();

        Account newAccount = accountService.CreateUser(firstname, lastname, balance);

        return ResponseEntity.ok(newAccount);
    }
}

class CreateAccountRequest {
    private String firstname;
    private String lastname;
    private double balance;

    public CreateAccountRequest() {
    }

    public CreateAccountRequest(String firstname, String lastname, double balance) {
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



