package TemperatureInTheHouse;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import TemperatureInTheHouse.controller.MainWindowController;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	MainWindowController controller;

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Temperature in my house");
		this.primaryStage.getIcons().add(new Image("resources/weather.png"));
		initRootLayout();
		showMainOverview();
	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);

			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showMainOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class
					.getResource("view/MainWindowLayout.fxml"));

			AnchorPane overview = (AnchorPane) loader.load();
			rootLayout.setCenter(overview);

			controller = loader.getController();
			controller.setMain(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Main refreshAllData() {
		System.gc();
		/*
		 * setCurrentDate(); setCurrentHour(); getTemperatureLastDay();
		 * getTemperatureLastHour();
		 */
		System.gc();
		return this;
	}

}
