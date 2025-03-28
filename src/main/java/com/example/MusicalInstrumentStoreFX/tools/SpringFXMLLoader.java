package com.example.MusicalInstrumentStoreFX.tools;

import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringFXMLLoader {

    private final ApplicationContext applicationContext;

    public SpringFXMLLoader(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    // Метод загрузки FXML с передачей пути
    public FXMLLoader load(String fxmlPath) {
        // Загружаем FXML с абсолютным путем
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        fxmlLoader.setControllerFactory(applicationContext::getBean); // Spring инжектирует контроллер
        return fxmlLoader;
    }
}
