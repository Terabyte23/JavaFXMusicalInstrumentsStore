package com.example.MusicalInstrumentStoreFX;

import com.example.MusicalInstrumentStoreFX.service.FormService;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MusicalInstrumentStoreFxApplication extends Application {
	public static ConfigurableApplicationContext applicationContext;
	public static Stage primaryStage;

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(MusicalInstrumentStoreFxApplication.class, args);
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		FormService formService = applicationContext.getBean(FormService.class);
		formService.loadLoginForm();

	}
}
