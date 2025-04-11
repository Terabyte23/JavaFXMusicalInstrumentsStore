package com.example.MusicalInstrumentStoreFX.model.repository;

import com.example.MusicalInstrumentStoreFX.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Метод для расчета дохода за день
    double calculateDailyRevenue(LocalDate date);

    // Метод для расчета дохода за месяц
    double calculateMonthlyRevenue(LocalDate date);

    // Метод для расчета дохода за год
    double calculateYearlyRevenue(LocalDate date);
}
