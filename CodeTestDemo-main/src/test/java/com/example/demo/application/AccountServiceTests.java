package com.example.demo.application;

import com.example.demo.domain.Account;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.infrastructure.IAccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

@SpringBootTest
public class AccountServiceTests {

    @Mock
    private IAccountRepository _accountRepo;
    @InjectMocks
    private AccountService _service;


    @Test
    public void CreateUser_ReturnsAccountWithGeneratedId_OnSuccess(){
        // Arrange
        String fName = "Test";
        String lName = "Account";
        double balance = 0.0;

        Account toReturn = Account.Create(
                42,
                fName,
                lName,
                balance
        );

        when(_accountRepo.save(any(Account.class))).thenReturn(toReturn);

        // Act
        Account result = _service.CreateUser(fName, lName, balance);

        // Assert
        assertNotNull(result);
        assertEquals(toReturn.get_id(), result.get_id());
        assertEquals(toReturn.get_firstname(), result.get_firstname());
        assertEquals(toReturn.get_lastname(), result.get_lastname());
        assertEquals(toReturn.get_balance(), result.get_balance());
    }

    @Test
    public void GetAccount_ReturnsAccount_WhenIdIsFound() {
        // Arrange
        Account testAccount = Account.Create(42, "Test", "User", 50);
        Optional<Account> toReturn = Optional.of(testAccount);

        when(_accountRepo.findById(any(Long.class))).thenReturn(toReturn);

        Account result;
        // Act
        try {
            result = _service.GetAccount(testAccount.get_id());
        } catch (AccountNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Assert
        assertNotNull(result);
        assertEquals(testAccount.get_id(), result.get_id());
    }

    @Test
    public void GetAccount_ThrowsAccountNotFoundException_WhenIdIsNotFound() {
        // Arrange
        Long testId = 42L;
        when(_accountRepo.findById(testId)).thenReturn(Optional.empty());
        // Act

        // Assert
        assertThrows(AccountNotFoundException.class, () -> _service.GetAccount(testId));
    }

    @Test
    public void Deposit_AddsToBalance_OnSuccess() throws AccountNotFoundException {
        // Arrange
        long id = 42;
        String firstname = "Test";
        String lastname = "Account";
        double initialBalance = 50;
        double amount = 10;

        Account toReturn = Account.Create(id, firstname, lastname, initialBalance);
        Account updated = Account.Create(id, firstname, lastname, initialBalance + amount);


        when(_accountRepo.findById(any())).thenReturn(Optional.of(toReturn));
        when(_accountRepo.save(any(Account.class))).thenReturn(updated);

        Account result;
        // Act
        result = _service.Deposit(toReturn.get_id(), amount);
        // Assert
        assertEquals(initialBalance + amount, result.get_balance());
    }

    @Test
    public void Deposit_ThrowsIllegalArgumentException_WhenAmountIsLessThanZero(){
        // Arrange
        long id = 42;
        double amount = -10;
        // Act

        // Assert
        assertThrows(IllegalArgumentException.class, () -> _service.Deposit(id, amount));
    }

    @Test
    public void Transfer_ReturnsUpdatedTransferOriginAccount_OnSuccess() throws AccountNotFoundException {
        // Arrange
        long fromId = 42;
        long toId = 45;
        double amount = 20;
        double initialBalance = 50;

        Account fromAccount = Account.Create(fromId, "Test", "Account", initialBalance);
        Account toAccount = Account.Create(toId, "Test", "Account2", 10);

        Account updatedFrom = Account.Create(fromId, "Test", "Account", initialBalance - amount);

        when(_accountRepo.findById(fromId)).thenReturn(Optional.of(fromAccount));
        when(_accountRepo.findById(toId)).thenReturn(Optional.of(toAccount));
        when(_accountRepo.save(fromAccount)).thenReturn(updatedFrom);

        // Act
        Account result = _service.Transfer(fromId, toId, amount);
        // Assert
        assertNotNull(result);
        assertEquals(initialBalance - amount, result.get_balance());
        verify(_accountRepo, times(1)).save(any(Account.class));
    }

    @Test
    public void Transfer_UpdatesTwoAccounts_OnSuccess() throws AccountNotFoundException {
        // Arrange
        long fromId = 42;
        long toId = 45;
        double amount = 20;

        Account fromAccount = Account.Create(fromId, "Test", "Account", 50);
        Account toAccount = Account.Create(toId, "Test", "Account2", 10);

        when(_accountRepo.findById(fromId)).thenReturn(Optional.of(fromAccount));
        when(_accountRepo.findById(toId)).thenReturn(Optional.of(toAccount));
        // Act

        _service.Transfer(fromId, toId, amount);
        // Assert
        verify(_accountRepo, times(2)).save(any(Account.class));
    }

    @Test
    public void Transfer_ThrowsIllegalArgumentException_WhenAmountIsLessThanZero() throws AccountNotFoundException {
        // Arrange
        long fromId = 42;
        long toId = 45;
        double amount = -20;

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> _service.Transfer(fromId, toId, amount));
    }



}
