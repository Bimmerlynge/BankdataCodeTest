package com.example.demo.application;

import com.example.demo.domain.Exchange;
import com.example.demo.infrastructure.ExchangeDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@PropertySource("classpath:api.properties")
public class ExchangeService implements IExchangeService {
    private final String apiKey;
    private final String hostAddress;
    private final RestTemplate restTemplate;


    public ExchangeService(@Value("${host}") String hostAddress, @Value("${apiKey}") String key, RestTemplate restTemplate) {
        this.hostAddress = hostAddress;
        this.apiKey = key;
        this.restTemplate = restTemplate;
    }

    public ExchangeDTO GetDKKToUSD(double amount){
        String uri = String.format(hostAddress + apiKey + "/pair/DKK/USD/" + amount);
        ResponseEntity<Exchange> response = restTemplate.getForEntity(uri, Exchange.class);
        return ExchangeDTO.From(amount,response.getBody().getConversionResult());
    }
}

