package com.example.MusicalInstrumentStoreFX.controller;

import com.example.MusicalInstrumentStoreFX.service.AppUserService;
import com.example.MusicalInstrumentStoreFX.service.FormService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class LoginFormController {
    private FormService formService;
    private AppUserService appUserService;

    @FXML
    private Label lbInfo;
    @FXML private TextField tfUsername;
    @FXML private PasswordField pfPassword;

    public LoginFormController(FormService formService, AppUserService appUserService) {
        this.formService = formService;
        this.appUserService = appUserService;
    }

    @FXML private void login(){
        if(appUserService.authentication(tfUsername.getText(),pfPassword.getText())){
            formService.loadMainForm();
        }else{
            lbInfo.setText("Нет такого пользователя, или неправильный пароль");
        }
    }
    @FXML private void showRegistrationForm(){
        formService.loadRegistrationForm("Создание нового пользователя");
    }
}