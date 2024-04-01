package com.example.demo.application;

import com.example.demo.domain.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@PropertySource("classpath:api.properties")
public class ExchangeService {
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
        return ExchangeDTO.From(amount, response.getBody().getConversionResult());
    }
}

class ExchangeDTO {
    private double DKK;
    private double USD;

    public ExchangeDTO(double DKK, double USD) {
        this.DKK = DKK;
        this.USD = USD;
    }

    public double getDKK() {
        return DKK;
    }

    public void setDKK(double DKK) {
        this.DKK = DKK;
    }

    public double getUSD() {
        return USD;
    }

    public void setUSD(double USD) {
        this.USD = USD;
    }

    public static ExchangeDTO From(double dkk, double usd){
        return new ExchangeDTO(dkk, usd);
    }
}
