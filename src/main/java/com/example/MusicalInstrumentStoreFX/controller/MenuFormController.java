package com.example.MusicalInstrumentStoreFX.controller;

import com.example.MusicalInstrumentStoreFX.service.AppUserService;
import com.example.MusicalInstrumentStoreFX.service.FormService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MenuFormController implements Initializable {
   private FormService formService;
   @FXML private Menu mInstruments;
   @FXML private Menu mAdmin;
   @FXML private Menu mUsers;
   @FXML private MenuItem miEnter;
   @FXML private MenuItem miProfile;
   @FXML private MenuItem miLogout;
   @FXML private Menu mBuyers;

   public MenuFormController(FormService formService) {
       this.formService = formService;
   }

   @FXML private void showBrandForm(){
       formService.loadBrandForm();
   }
   @FXML private void showInstrumentForm(){
       formService.loadNewInstrumentForm();
   }
   @FXML private void showLoginForm(){
       formService.loadLoginForm();
   }
   @FXML private void logout(){
       AppUserService.currentUser = null;
       formService.loadLoginForm();
   }
   @FXML private void miAddBuyer(){
        formService.loadNewBuyersForm();
   }
   @FXML private void miListBuyers(){
        formService.loadListBuyersForm();
   }
    private void initMenuVisible(){
        if (AppUserService.currentUser.getRoles().contains(AppUserService.ROLES.ADMINISTRATOR.toString())) {
            mInstruments.setVisible(true);
            mAdmin.setVisible(true);
            mUsers.setVisible(true);
            miEnter.setVisible(false);
            miProfile.setVisible(true);
            miLogout.setVisible(true);
            mBuyers.setVisible(true);
        } else if(AppUserService.currentUser.getRoles().contains(AppUserService.ROLES.MANAGER.toString())){
            mInstruments.setVisible(true);
            mAdmin.setVisible(false);
            mUsers.setVisible(true);
            miEnter.setVisible(false);
            miProfile.setVisible(true);
            miLogout.setVisible(true);
            mBuyers.setVisible(false);
        } else if(AppUserService.currentUser.getRoles().contains(AppUserService.ROLES.USER.toString())){
            mInstruments.setVisible(false);
            mAdmin.setVisible(false);
            mUsers.setVisible(true);
            miEnter.setVisible(false);
            miProfile.setVisible(true);
            miLogout.setVisible(true);
            mBuyers.setVisible(false);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMenuVisible();
    }
}
