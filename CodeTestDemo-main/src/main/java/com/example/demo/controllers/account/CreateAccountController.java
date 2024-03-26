package com.example.demo.controllers.account;

import com.example.demo.domain.Account;
import com.example.demo.infrastructure.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/account")
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest accountRequest) {
        String firstname = accountRequest.GetFirstname();
        String lastname = accountRequest.GetLastname();
        double balance = accountRequest.GetBalance();

        Account newAccount = _accountService.CreateUser(firstname, lastname, balance);

        System.out.println("New account: " + newAccount.toString());

        return ResponseEntity.ok(newAccount);
    }

    static class AccountRequest{
        private String firstname;
        private String lastname;
        private double balance;

        public AccountRequest(String firstname, String lastname, double balance) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.balance = balance;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public String GetFirstname() {
            return firstname;
        }

        public String GetLastname() {
            return lastname;
        }

        public double GetBalance() {
            return balance;
        }
    }
}
