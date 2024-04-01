package com.example.demo.controllers.account;

import com.example.demo.application.AccountService;
import com.example.demo.domain.Account;
import com.example.demo.exceptions.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PutTransferMoneyController {
    private final AccountService _accountService;

    @Autowired
    public PutTransferMoneyController(AccountService accountService) {
        _accountService = accountService;
    }

    @PutMapping("/transfer")
    public ResponseEntity<?> Put(@RequestBody TransferMoneyRequest request) throws AccountNotFoundException {
        Account updated = _accountService.Transfer(request.getFromId(), request.getToId(), request.getAmount());
        return ResponseEntity.ok(updated);
    }
}

class TransferMoneyRequest{
    private long fromId;
    private long toId;
    private double amount;

    public TransferMoneyRequest(long fromId, long toId, double amount) {
        this.fromId = fromId;
        this.toId = toId;
        this.amount = amount;
    }

    public long getFromId() {
        return fromId;
    }

    public void setFromId(long fromId) {
        this.fromId = fromId;
    }

    public long getToId() {
        return toId;
    }

    public void setToId(long toId) {
        this.toId = toId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
