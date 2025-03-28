package com.example.MusicalInstrumentStoreFX.controller;

import com.example.MusicalInstrumentStoreFX.model.entity.Brand;
import com.example.MusicalInstrumentStoreFX.service.BrandService;
import com.example.MusicalInstrumentStoreFX.service.FormService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class BrandFormController implements Initializable {

    private FormService formService;
    private BrandService brandService;
    @FXML private TextField tfFirstName;

    public BrandFormController(FormService formService, BrandService brandService) {
        this.formService = formService;
        this.brandService = brandService;
    }

    @FXML private void create() throws IOException {
        Brand brand = new Brand();
        brand.setFirstName(tfFirstName.getText());
        brandService.add(brand);
        formService.loadMainForm();
    }
    @FXML private void goToMainForm() throws IOException {
        formService.loadMainForm();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
