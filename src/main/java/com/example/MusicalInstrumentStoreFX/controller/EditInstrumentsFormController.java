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

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class EditInstrumentsFormController implements Initializable {
    private FormService formService;
    private InstrumentsService instrumentsService;
    private BrandService brandService;
    private Instruments editInstruments;

    @FXML
    private TextField tfId;
    @FXML private TextField tfTitle;
    @FXML private ListView<Brand> lvBrands;
    @FXML private TextField tfPublicationYear;
    @FXML private TextField tfQuantity;
    @FXML private TextField tfCount;


    public EditInstrumentsFormController( FormService formService, InstrumentsService instrumentsService, BrandService brandService) {
        this.formService = formService;
        this.instrumentsService = instrumentsService;
        this.brandService = brandService;
    }
    @FXML private void goEdit(){
        editInstruments.setTitle(tfTitle.getText());
        editInstruments.getBrand().clear(); // Очищает текущий список брендов
        editInstruments.getBrand().addAll(lvBrands.getSelectionModel().getSelectedItems());
        editInstruments.setPublicationYear(Integer.parseInt(tfPublicationYear.getText()));
        editInstruments.setQuantity(Integer.parseInt(tfQuantity.getText()));
        editInstruments.setCount(editInstruments.getQuantity());
        instrumentsService.create(editInstruments);
        formService.loadMainForm();
    }

    @FXML private void goToMainForm() throws IOException {
        formService.loadMainForm();
    }

    @FXML
    private void deleteInstrument() {
        Long instrumentId = Long.parseLong(tfId.getText()); // Получите ID из текстового поля
        instrumentsService.deleteInstrumentAndResortIds(instrumentId);
    }



    public void setEditInstruments(Instruments editInstruments) {
        this.editInstruments = editInstruments;
        tfId.setText(editInstruments.getId().toString());
        tfTitle.setText(editInstruments.getTitle());
        tfPublicationYear.setText(((Integer) editInstruments.getPublicationYear()).toString());
        tfQuantity.setText(((Integer) editInstruments.getQuantity()).toString());
        tfCount.setText(((Integer) editInstruments.getCount()).toString());

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lvBrands.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        List<Brand> authors = brandService.getListBrand();
        lvBrands.getItems().setAll(FXCollections.observableArrayList(authors));
        lvBrands.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Brand brand, boolean empty) {
                super.updateItem(brand, empty);
                if (empty || brand == null) {
                    setText(null);
                } else {
                    setText("ID: " + brand.getId() + " - " + brand.getFirstName());
                }
            }
        });
    }

}