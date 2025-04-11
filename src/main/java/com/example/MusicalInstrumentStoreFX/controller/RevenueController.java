package com.example.MusicalInstrumentStoreFX.controller;

import com.example.MusicalInstrumentStoreFX.service.RevenueService;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import java.time.LocalDate;

public class RevenueController {

    @FXML private Text headerText;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> periodComboBox;
    @FXML private Label revenueLabel;

    private RevenueService revenueService;

    // Конструктор с инъекцией зависимостей через Spring
    public RevenueController(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    // Метод, вызываемый при нажатии на кнопку "Рассчитать доход"
    @FXML
    private void onCalculateRevenue() {
        if (datePicker.getValue() == null || periodComboBox.getValue() == null) {
            revenueLabel.setText("Пожалуйста, выберите дату и период.");
            return;
        }

        LocalDate selectedDate = datePicker.getValue();
        String selectedPeriod = periodComboBox.getValue();

        double revenue = 0.0;

        switch (selectedPeriod) {
            case "День":
                revenue = revenueService.calculateRevenueForDay(selectedDate);
                break;
            case "Месяц":
                revenue = revenueService.calculateRevenueForMonth(selectedDate);
                break;
            case "Год":
                revenue = revenueService.calculateRevenueForYear(selectedDate);
                break;
        }

        revenueLabel.setText("Доход: " + revenue + " евро.");
    }
}
