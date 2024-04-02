package com.example.demo.application;

import com.example.demo.domain.Account;
import com.example.demo.exceptions.AccountNotFoundException;

import java.util.List;

public interface IAccountService {
    public Account CreateUser(String firstname, String lastname, double balance);
    public Account GetAccount(Long id) throws AccountNotFoundException;
    public Account Deposit(long id, double toDeposit) throws AccountNotFoundException;
    public Account Withdraw(long id, double toWithdraw) throws AccountNotFoundException;
    public Account Transfer(long fromId, long toId, double amount) throws AccountNotFoundException;
    List<Account> GetAll();
}
