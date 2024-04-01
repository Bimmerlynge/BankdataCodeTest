package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Exchange implements Serializable {
    @JsonProperty("conversion_result")
    private double conversionResult;

    public Exchange() {
    }

    public double getConversionResult() {
        return conversionResult;
    }

    public void setConversionResult(double conversionResult) {
        this.conversionResult = conversionResult;
    }
}
