package services;

import services.model.Services;
import services.view.MainWindowController;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	Services servicesMain;
	MainWindowController controller;
	private Stage primaryStage;
	private BorderPane rootLayout;
	FileChooser fileChooser ;
	@Override
	public void start(final Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Services for month");
			this.primaryStage.setResizable(false);
			
			initRootLayout();
			showMainOverview();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	void initRootLayout() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
		rootLayout = (BorderPane) loader.load();
		Scene scene = new Scene(rootLayout);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void showMainOverview(){
		try {
		   	FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainWindowLayout.fxml"));
		
			AnchorPane overview = (AnchorPane) loader.load();
			rootLayout.setCenter(overview);
			
			controller = loader.getController();
			controller.setMain(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage(){
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
