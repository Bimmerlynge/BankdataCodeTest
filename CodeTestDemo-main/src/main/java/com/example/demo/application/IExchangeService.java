package com.example.demo.application;

import com.example.demo.infrastructure.ExchangeDTO;
import org.springframework.stereotype.Service;

public interface IExchangeService {
    public ExchangeDTO GetDKKToUSD(double amount);
}
