package com.example.MusicalInstrumentStoreFX.service;

import com.example.MusicalInstrumentStoreFX.model.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RevenueService {

    private final TransactionRepository transactionRepository;

    public RevenueService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public double calculateRevenueForDay(LocalDate date) {
        // Логика для расчета дохода за день
        return transactionRepository.calculateDailyRevenue(date);
    }

    public double calculateRevenueForMonth(LocalDate date) {
        // Логика для расчета дохода за месяц
        return transactionRepository.calculateMonthlyRevenue(date);
    }

    public double calculateRevenueForYear(LocalDate date) {
        // Логика для расчета дохода за год
        return transactionRepository.calculateYearlyRevenue(date);
    }
}
