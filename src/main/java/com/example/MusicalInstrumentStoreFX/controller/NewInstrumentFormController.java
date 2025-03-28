package com.example.MusicalInstrumentStoreFX.controller;

import com.example.MusicalInstrumentStoreFX.model.entity.Brand;
import com.example.MusicalInstrumentStoreFX.model.entity.Instruments;
import com.example.MusicalInstrumentStoreFX.service.BrandService;
import com.example.MusicalInstrumentStoreFX.service.FormService;
import com.example.MusicalInstrumentStoreFX.service.InstrumentsService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class NewInstrumentFormController implements Initializable {

    private FormService formService;
    private InstrumentsService instrumentsService;
    private BrandService brandService;

    @FXML private TextField tfTitle;
    @FXML private ListView<Brand> lvBrands;
    @FXML private TextField tfPublicationYear;
    @FXML private TextField tfQuantity;

    public NewInstrumentFormController(FormService formService, InstrumentsService instrumentsService, BrandService brandService) {
        this.formService = formService;
        this.instrumentsService = instrumentsService;
        this.brandService = brandService;
    }
    @FXML private void create(){
        Instruments instruments = new Instruments();
        instruments.setTitle(tfTitle.getText());
        instruments.getBrand().addAll(lvBrands.getSelectionModel().getSelectedItems());
        instruments.setPublicationYear(Integer.parseInt(tfPublicationYear.getText()));
        instruments.setQuantity(Integer.parseInt(tfQuantity.getText()));
        instruments.setCount(instruments.getQuantity());
        instrumentsService.create(instruments);
        formService.loadMainForm();
    }

    @FXML private void goToMainForm(){
        formService.loadMainForm();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lvBrands.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        List<Brand> brands = brandService.getListBrand();
        lvBrands.getItems().setAll(FXCollections.observableArrayList(brands));
        lvBrands.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Brand brand, boolean empty) {
                super.updateItem(brand, empty);
                if (empty || brand == null) {
                    setText(null);
                }else {
                    setText(brand.getId() + ". " + brand.getFirstName());
                }
            }
        });
    }
}
