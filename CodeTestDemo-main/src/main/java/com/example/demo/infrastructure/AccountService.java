package com.example.demo.infrastructure;

import com.example.demo.domain.Account;
import com.example.demo.exceptions.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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
}
