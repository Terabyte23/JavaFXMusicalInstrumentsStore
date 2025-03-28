package com.example.MusicalInstrumentStoreFX.controller;

import com.example.MusicalInstrumentStoreFX.model.entity.Instruments;
import com.example.MusicalInstrumentStoreFX.service.FormService;
import com.example.MusicalInstrumentStoreFX.service.InstrumentsService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sound.midi.Instrument;

@Component
public class SelectedInstrumentFormController {

    @Autowired
    private MainFormController mainFormController;


    @FXML private VBox vbSelectedInstrumentRoot;
    private Instruments instruments;
    private final FormService formService;
    private final InstrumentsService instrumentsService;


    // Инжектируем FormService и InstrumentsService
    public SelectedInstrumentFormController(FormService formService, InstrumentsService instrumentsService) {
        this.formService = formService;
        this.instrumentsService = instrumentsService;
    }

    // Устанавливаем инструмент, который был выбран
    public void setInstruments(Instruments instruments) {
        this.instruments = instruments;
    }

    // Метод для установки ссылки на MainFormController
    public void setMainFormController(MainFormController mainFormController) {
        this.mainFormController = mainFormController;
    }

    @FXML private TextField tfQuantity; // Поле для ввода количества

    // Метод для заказа инструмента
    @FXML private void takeOnInstrument() {
        try {
            // Получаем введенное количество
            int quantityToBuy = Integer.parseInt(tfQuantity.getText());

            // Проверяем, что введено корректное количество
            if (quantityToBuy <= 0) {
                showAlert("Ошибка", "Количество должно быть больше 0!", AlertType.ERROR);
                return;
            }

            // Проверяем, есть ли инструменты в наличии
            if (instruments.getCount() >= quantityToBuy) {
                // Уменьшаем количество инструмента на введенное количество
                instruments.setCount(instruments.getCount() - quantityToBuy);

                // Обновляем инструмент в базе данных
                instrumentsService.update(instruments);

                // Обновляем таблицу в MainFormController
                mainFormController.updateTable();

                // Закрываем окно после успешной операции
                Stage stage = (Stage) vbSelectedInstrumentRoot.getScene().getWindow();
                stage.close();

                // Показываем сообщение об успешной операции
                showAlert("Успех", "Инструмент заказан!", AlertType.INFORMATION);
            } else {
                // Если недостаточно товара, показываем сообщение об ошибке
                showAlert("Ошибка", "Недостаточно товара в наличии! Доступно: " + instruments.getCount(), AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Пожалуйста, введите корректное количество!", AlertType.ERROR);
        }
    }


    // Метод для возврата инструмента
    @FXML private void returnInstrument() {
        try {
            int quantityToReturn = Integer.parseInt(tfQuantity.getText());

            // Проверяем, что количество больше нуля
            if (quantityToReturn <= 0) {
                showAlert("Ошибка", "Количество должно быть больше 0!", AlertType.ERROR);
                return;
            }

            // Увеличиваем количество инструмента на введенное количество
            instruments.setCount(instruments.getCount() + quantityToReturn);

            // Обновляем инструмент в базе данных
            instrumentsService.update(instruments);

            // Обновляем таблицу в MainFormController
            if (mainFormController != null) {
                mainFormController.updateTable();
            }

            // Закрываем окно после успешной операции
            Stage stage = (Stage) vbSelectedInstrumentRoot.getScene().getWindow();
            stage.close();

            // Показываем сообщение об успешном возврате
            showAlert("Успех", "Инструмент возвращен!", AlertType.INFORMATION);

        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Пожалуйста, введите корректное количество!", AlertType.ERROR);
        }
    }

    // Метод для отображения всплывающих сообщений
    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML private void openInstrumentsDetails(Instruments instruments) {
        formService.loadSelectedInstrumentFormModality(instruments);
    }

}
