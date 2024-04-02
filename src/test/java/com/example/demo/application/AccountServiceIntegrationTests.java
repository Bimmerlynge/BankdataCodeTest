package com.example.demo.application;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.infrastructure.IAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AccountServiceIntegrationTests {
    @Autowired
    private IAccountRepository accountRepository;

    private AccountService accountService;

    @BeforeEach
    public void SetUp(){
        accountService = new AccountService(accountRepository);
    }

    @Test
    public void CreateUser_InsertsOneRow_OnSuccess(){
        // Arrange
        var countBefore = accountRepository.count();

        // Act
        accountService.CreateUser("Navn", "Navn", 10);

        // Assert
        var countAfter = accountRepository.count();
        assertEquals(countBefore + 1 , countAfter);
    }
}
