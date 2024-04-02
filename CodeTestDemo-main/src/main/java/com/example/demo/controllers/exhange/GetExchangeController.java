package com.example.demo.controllers.exhange;

import com.example.demo.application.IExchangeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetExchangeController {

    private final IExchangeService service;

    @Autowired
    public GetExchangeController(IExchangeService service) {
        this.service = service;
    }

    @GetMapping("/dkk-to-usd")
    @Tag(name = "Exchange")
    public ResponseEntity<?> Get(@RequestParam Double amount){
        var response = service.GetDKKToUSD(amount);

        return ResponseEntity.ok(response);
    }
}
