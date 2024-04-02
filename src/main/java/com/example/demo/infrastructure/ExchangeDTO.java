package com.example.demo.infrastructure;

public class ExchangeDTO {
    private double DKK;
    private double USD;

    private ExchangeDTO(double DKK, double USD) {
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

    public static ExchangeDTO From(double dkk, double usd) {
        return new ExchangeDTO(dkk, usd);
    }
}
