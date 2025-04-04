package com.example.MusicalInstrumentStoreFX.controller;

import com.example.MusicalInstrumentStoreFX.model.entity.AppUser;
import com.example.MusicalInstrumentStoreFX.service.AppUserService;
import com.example.MusicalInstrumentStoreFX.service.FormService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class EditBuyerFormController implements Initializable {

    private FormService formService;
    private AppUserService appUserService;
    private AppUser editBuyer; // Пользователь, которого редактируем

    @FXML private TextField tfId;
    @FXML private TextField tfLogin;
    @FXML private TextField tfFirstName;
    @FXML private TextField tfLastName;
    @FXML private ComboBox<String> rolesComboBox;  // Для ролей

    // Публичный конструктор без параметров
    public EditBuyerFormController() {
        // Пустой конструктор для работы с FXML
    }

    // Конструктор с инъекцией зависимостей через Spring
    public EditBuyerFormController(FormService formService, AppUserService appUserService) {
        this.formService = formService;
        this.appUserService = appUserService;
    }

    @FXML
    private void saveBuyer() {
        if (appUserService == null) {
            System.out.println("appUserService is null!");
            return; // или выбрасывать исключение, если это критично
        }

        editBuyer.setUsername(tfLogin.getText());
        editBuyer.setFirstname(tfFirstName.getText());
        editBuyer.setLastname(tfLastName.getText());
        editBuyer.setRoles(new HashSet<>(List.of(rolesComboBox.getValue() != null ? rolesComboBox.getValue() : "USER")));
        appUserService.save(editBuyer);  // Сохраняем пользователя
        formService.loadMainForm();  // Возвращаемся на главную
    }

    @FXML
    private void cancelEdit() {
        formService.loadMainForm();
    }

    public void setEditBuyer(AppUser editBuyer) {
        // Инициализация формы данными пользователя
        this.editBuyer = editBuyer;
        tfId.setText(editBuyer.getId().toString());
        tfLogin.setText(editBuyer.getUsername());
        tfFirstName.setText(editBuyer.getFirstname());
        tfLastName.setText(editBuyer.getLastname());

        // Преобразуем Set в List
        List<String> rolesList = new ArrayList<>(editBuyer.getRoles());

        // Устанавливаем роли в ComboBox
        rolesComboBox.getItems().setAll("USER", "ADMIN"); // Пример ролей

        // Проверяем, если Set содержит хотя бы один элемент, выбираем его
        if (!rolesList.isEmpty()) {
            rolesComboBox.getSelectionModel().select(rolesList.get(0)); // Выбираем текущую роль
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Инициализация ComboBox для ролей (например, "USER", "ADMIN")
        rolesComboBox.getItems().setAll("USER", "ADMIN");
    }
}
