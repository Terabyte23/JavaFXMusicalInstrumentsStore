package com.example.MusicalInstrumentStoreFX.controller;

import com.example.MusicalInstrumentStoreFX.model.entity.AppUser;
import com.example.MusicalInstrumentStoreFX.service.AppUserService;
import com.example.MusicalInstrumentStoreFX.service.FormService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ListBuyersFormController {

    private final AppUserService appUserService;
    private final FormService formService;

    @FXML
    private TableView<AppUser> tvListBuyers; // TableView для отображения пользователей
    @FXML
    private TableColumn<AppUser, Long> tcIdBuyers; // Колонка для ID
    @FXML
    private TableColumn<AppUser, String> tcUsernameBuyers; // Колонка для Логина
    @FXML
    private TableColumn<AppUser, String> tcFirstnameBuyers; // Колонка для Имени
    @FXML
    private TableColumn<AppUser, String> tcLastnameBuyers; // Колонка для Фамилии
    @FXML
    private TableColumn<AppUser, String> tcRolesBuyers; // Колонка для Ролей
    @FXML
    private Button editBuyerButton; // Кнопка редактирования

    public ListBuyersFormController(AppUserService appUserService, FormService formService) {
        this.appUserService = appUserService;
        this.formService = formService;
    }

    @FXML
    public void initialize() {
        // Настройка колонок TableView с использованием SimpleXXXProperty
        tcIdBuyers.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getId()).asObject());
        tcUsernameBuyers.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        tcFirstnameBuyers.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstname()));
        tcLastnameBuyers.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastname()));
        tcRolesBuyers.setCellValueFactory(cellData -> new SimpleStringProperty(String.join(", ", cellData.getValue().getRoles())));

        // Загрузка пользователей в таблицу
        loadBuyers();

        // Защита от NullPointerException: проверяем, что кнопка не null
        if (editBuyerButton != null) {
            // Устанавливаем кнопку редактирования невидимой по умолчанию
            editBuyerButton.setVisible(false);

            // Добавляем обработчик на выбор пользователя в таблице
            tvListBuyers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                // Если выбран пользователь, показываем кнопку редактирования
                editBuyerButton.setVisible(newValue != null);
            });
        }
    }


    private void loadBuyers() {
        // Получение списка всех пользователей через сервис
        List<AppUser> buyers = appUserService.getAllUsers();
        tvListBuyers.getItems().setAll(buyers); // Добавляем пользователей в TableView
    }

    // Метод для открытия формы редактирования пользователя
    @FXML
    private void showEditBuyersForm(ActionEvent event) {
        AppUser selectedBuyer = tvListBuyers.getSelectionModel().getSelectedItem();
        if (selectedBuyer != null) {
            // Если покупатель выбран, открываем форму редактирования
            formService.loadEditBuyersForm(selectedBuyer);
        }
    }

    @FXML
    private void showDeleteBuyersForm(ActionEvent event) {
        System.out.println("Удаление покупателя");
        // Реализуйте логику удаления покупателя позже
    }

    @FXML
    private void goBackToMainForm() {
        formService.loadMainForm();
    }

    public void updateBuyer(AppUser updatedBuyer) {
        // Обновляем список пользователей в основном экране
        List<AppUser> users = appUserService.getAllUsers();
        tvListBuyers.getItems().setAll(users); // Обновляем TableView с пользователями
    }

    @FXML
    private void onEditButtonClick() {
        AppUser selectedUser = tvListBuyers.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            formService.loadEditBuyersForm(selectedUser);  // Загружаем форму редактирования
        }
    }


}
