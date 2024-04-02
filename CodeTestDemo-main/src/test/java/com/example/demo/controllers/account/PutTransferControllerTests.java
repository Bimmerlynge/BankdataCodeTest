package com.example.demo.controllers.account;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.example.demo.application.AccountService;
import com.example.demo.domain.Account;
import com.example.demo.exceptions.AccountNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class PutTransferControllerTests {

    @InjectMocks
    private TransferMoneyController controller;
    @Mock
    private AccountService accountService;


    @Test
    public void test() throws AccountNotFoundException {
        // Arrange
        Account initAccount = Account.Create(1, "Mathias", "Lynge", 45);
        TransferMoneyRequest request = new TransferMoneyRequest(initAccount.get_id(), 2, 10);
        Account updated = Account.Create(1, "Mathias", "Lynge", 35);
        when(accountService.Transfer(1, 2, 10)).thenReturn(updated);

        // Act
        ResponseEntity<?> result = controller.Put(request);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void test2() throws AccountNotFoundException {
        // Arrange
        Account initAccount = Account.Create(1, "Mathias", "Lynge", 45);
        TransferMoneyRequest request = new TransferMoneyRequest(initAccount.get_id(), 2, 10);
        Account updated = Account.Create(1, "Mathias", "Lynge", 35);
        when(accountService.Transfer(1, 2, 10)).thenThrow(AccountNotFoundException.class);

        // Act


        // Assert
        assertThrows(AccountNotFoundException.class, () -> controller.Put(request));
    }
}
