package com.example.demo.application;

import com.example.demo.domain.Account;
import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.infrastructure.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

//TODO some CQRS would be nice here instead of monolithic class
@Service
public class AccountService {
    private final IAccountRepository _accountRepository;

    @Autowired
    public AccountService(IAccountRepository accountRepository) {
        _accountRepository = accountRepository;
    }


    public Account CreateUser(String firstname, String lastname, double balance){
        Account newAccount = Account.Create(firstname, lastname, balance);
        return _accountRepository.save(newAccount);
    }

    public Account GetAccount(Long id) throws AccountNotFoundException {
        Optional<Account> account = _accountRepository.findById(id);

        if (account.isEmpty())
            throw new AccountNotFoundException(String.format("Account with id: %d was not found", id));

        return account.get();
    }

    public Account Deposit(long id, double toDeposit) throws AccountNotFoundException, IllegalArgumentException {
        if (toDeposit < 0)
            throw new IllegalArgumentException("Can't deposit negative values");

        Account existing = GetAccount(id);

        double newValue = existing.get_balance() + toDeposit;
        existing.set_balance(newValue);

        return _accountRepository.save(existing);
    }

    public Account Withdraw(long id, double toWithdraw) throws AccountNotFoundException, IllegalArgumentException {
        if (toWithdraw < 0){
            throw new IllegalArgumentException("Can't withdraw negative values");
        }

        Account existing = GetAccount(id);

        double newValue = existing.get_balance() - toWithdraw;
        existing.set_balance(newValue);

        return _accountRepository.save(existing);
    }

    public Account Transfer(long fromId, long toId, double amount) throws AccountNotFoundException {
        if (amount < 0){
            throw new IllegalArgumentException("Can't transfer negative values");
        }

        Account from = GetAccount(fromId);
        Account to = GetAccount(toId);

        Deposit(to.get_id(), amount);
        return Withdraw(from.get_id(), amount);
    }
}
