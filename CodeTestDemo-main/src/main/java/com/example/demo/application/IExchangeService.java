package com.example.demo.application;

import com.example.demo.infrastructure.ExchangeDTO;

public interface IExchangeService {
    public ExchangeDTO GetDKKToUSD(double amount);
}
