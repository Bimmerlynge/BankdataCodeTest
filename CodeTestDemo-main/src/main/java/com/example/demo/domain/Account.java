package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long _id;
    private String _firstname;
    private String _lastname;
    private double _balance;


    public Account() {
    }

    private Account(long id, String firstname, String lastname, double balance) {
        this._id = id;
        this._firstname = firstname;
        this._lastname = lastname;
        this._balance = balance;
    }

    public static Account Create(String firstname, String lastname, double balance){
        return new Account(-1, firstname, lastname, balance);
    }

    public static Account Create(long id, String firstname, String lastname, double balance){
        return new Account(id, firstname, lastname, balance);
    }

    public long get_id() {
        return _id;
    }

    public String get_firstname() {
        return _firstname;
    }

    public String get_lastname() {
        return _lastname;
    }

    public double get_balance() {
        return _balance;
    }
}
