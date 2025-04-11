package com.example.MusicalInstrumentStoreFX.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    private Long id;
    private LocalDate date;
    private double amount;

    // Геттеры и сеттеры

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
