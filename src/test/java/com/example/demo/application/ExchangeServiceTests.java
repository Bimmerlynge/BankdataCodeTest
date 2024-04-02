package com.example.demo.application;

import com.example.demo.domain.Exchange;
import com.example.demo.infrastructure.ExchangeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:api.properties")
public class ExchangeServiceTests {
    private String hostAddr = "https://v6.exchangerate-api.com/v6/";
    private String apiKey = "1b151d336346dd5286b50e29";
    @Mock
    private RestTemplate restTemplate;

    private ExchangeService service;

    @BeforeEach
    void setUp() {
        service = new ExchangeService(hostAddr, apiKey, restTemplate);
    }

    @Test
    public void GetDKKToUSD_CallsCorrectEndpoint(){
        // Arrange
        double amount = 100;
        Exchange toReturn = new Exchange();
        toReturn.setConversionResult(amount);

        when(restTemplate.getForEntity(any(String.class), eq(Exchange.class))).thenReturn(ResponseEntity.ok(toReturn));

        // Act
        service.GetDKKToUSD(amount);

        // Assert
        verify(restTemplate, times(1)).getForEntity(hostAddr + apiKey + "/pair/DKK/USD/" + amount, Exchange.class);
    }

    @Test
    public void GetDKKToUSD_ReturnsExchangeDTOOnSuccess(){
        // Arrange
        double amount = 100;
        double usdAmount = 50;
        Exchange expected = new Exchange(usdAmount);

        when(restTemplate.getForEntity(any(String.class), eq(Exchange.class))).thenReturn(ResponseEntity.ok(expected));
        // Act
        ExchangeDTO result = service.GetDKKToUSD(amount);
        // Assert
        assertNotNull(result);
        assertEquals(amount, result.getDKK());
        assertEquals(usdAmount, result.getUSD());
    }

    @Test
    public void GetDKKToUSD_ThrowsHttpClientErrorException_WhenAmountIsLessThanZero(){
        // Arrange
        double amount = -10;
        when(restTemplate.getForEntity(any(String.class), eq(Exchange.class))).thenThrow(HttpClientErrorException.class);
        // Act
        // Assert
        assertThrows(HttpClientErrorException.class, () -> service.GetDKKToUSD(amount));
    }


}
