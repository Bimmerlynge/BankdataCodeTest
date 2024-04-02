package com.example.demo.controllers.account;

import com.example.demo.application.IAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetAllController {

    private final IAccountService service;

    @Autowired
    public GetAllController(IAccountService service) {
        this.service = service;
    }

    @GetMapping("/accounts")
    @Tag(name="Accounts")
    public ResponseEntity<?> Get(){
        return ResponseEntity.ok(service.GetAll());
    }
}
