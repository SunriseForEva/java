package services.view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import com.sun.jnlp.ApiDialog;
import javafx.scene.control.Alert;
import org.controlsfx.dialog.Dialogs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import services.model.*;
import services.*;

@SuppressWarnings("deprecation")
public class MainWindowController {
	Services servicesController ;
	Main main;
	
	@FXML
	private TextField adrressCity;
	@FXML
	private TextField adrressStreet;
	@FXML
	private TextField adrressBuilding;
	@FXML
	private TextField adrressFlat;
	
	@FXML
	private CheckBox gazWithMeter = new CheckBox();
	@FXML
	private CheckBox gazWithoutMeter = new CheckBox();;
	@FXML
	private TextField gazAccaunt;
	@FXML
	private TextField gazTariff;
	@FXML
	private TextField gazCurrentTestimonies;
	@FXML
	private TextField gazPreveriusTestimonies;
	@FXML
	private TextField gazDifferencInTestimonies;
	@FXML
	private TextField gazSum;
	@FXML
	private TextField gazWithoutMeterAccaunt;
	@FXML
	private TextField gazWithoutMeterTariff;
	@FXML
	private TextField gazWithoutMeterSum;
	
	@FXML
	private TextField electricityAccaunt;
	@FXML
	private TextField electricityTariff;
	@FXML
	private TextField electricityTariff2;
	@FXML
	private TextField electricityCurrentTestimonies;
	@FXML
	private TextField electricityPreveriusTestimonies;
	@FXML
	private TextField electricityDifferencInTestimonies;
	@FXML
	private TextField electricitySum;
	
	@FXML
	private CheckBox waterWithMeter = new CheckBox();
	@FXML
	private CheckBox waterWithoutMeter = new CheckBox();
	@FXML
	private TextField waterAccaunt;
	@FXML
	private TextField waterTariff;
	@FXML
	private TextField waterCurrentTestimonies;
	@FXML
	private TextField waterPreveriusTestimonies;
	@FXML
	private TextField waterDifferencInTestimonies;
	@FXML
	private TextField waterSum;
	@FXML
	private TextField waterWithoutMeterAccaunt;
	@FXML
	private TextField waterWithoutMeterTariff;
	@FXML
	private TextField waterWithoutMeterSum;
	
	@FXML
	private TextField housingOfficeAccaunt;
	@FXML
	private TextField housingOfficeTariff;
	@FXML
	private TextField housingOfficeSum;
	
	@FXML 
	private Button buttonOpenAddress;
	@FXML 
	private Button buttonClear;
	@FXML 
	private Button buttonSave;
	@FXML 
	private Button buttonCalculation;
	@FXML
	private TextField totalSum;
	
	public MainWindowController() {
			
	}

	@FXML
	public void initialize(){
		buttonCalculation.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try{
					if(adrressCity.getText().equals("") || adrressStreet.getText().equals("") ||
							adrressBuilding.getText().equals("") ||	adrressFlat.getText().equals(""))
					{
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setContentText("Необходимо заполнить поля адреса!");
						alert.setTitle("Заполните необходимые поля!");
						alert.setHeaderText("Внимание!");
						alert.show();
					}
					
					if(!(waterWithMeter.isSelected() || (waterWithoutMeter.isSelected()))){
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setContentText("Вода. Необходимо выбрать тип\nпоказаний (по счетчику или нет) !");
						alert.setTitle("Заполните необходимые поля!");
						alert.setHeaderText("Внимание!");
						alert.show();
					}
					
					if(!(gazWithMeter.isSelected() || (gazWithoutMeter.isSelected()))){
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setContentText("Газ. Необходимо выбрать тип\nпоказаний (по счетчику или нет) !");
						alert.setTitle("Заполните необходимые поля!");
						alert.setHeaderText("Внимание!");
						alert.show();
					}
					
			    	servicesController = new Services();
			    	Adrress adrressController = new Adrress(adrressCity.getText(),
					adrressStreet.getText(),
					adrressBuilding.getText(), 
					adrressFlat.getText());
					
					ElectricityAccount electricityAccountController = new ElectricityAccount(Integer.parseInt(electricityAccaunt.getText()),
						Float.parseFloat(electricityTariff.getText()),
						Integer.parseInt(electricityCurrentTestimonies.getText()),
						Integer.parseInt(electricityPreveriusTestimonies.getText()),
						Float.parseFloat(electricityTariff2.getText()));
					
					GasAccaunt gasAccauntController;
					if(gazWithMeter.isSelected()){
					    gasAccauntController = new GasAccaunt(Integer.parseInt(gazAccaunt.getText()),
						    Float.parseFloat(gazTariff.getText()),
						    Integer.parseInt(gazCurrentTestimonies.getText()),
						    Integer.parseInt(gazPreveriusTestimonies.getText()));
					}
					
					else
					{
					    gasAccauntController = new GasAccaunt(Integer.parseInt(gazWithoutMeterAccaunt.getText()), Float.parseFloat(gazWithoutMeterTariff.getText()));
					}
					
					WaterAccaunt waterAccauntController;
					if(waterWithMeter.isSelected()){
	        				waterAccauntController = new WaterAccaunt(Integer.parseInt(waterAccaunt.getText()),
	        					Float.parseFloat(waterTariff.getText()),
	        					Integer.parseInt(waterCurrentTestimonies.getText()),
	        					Integer.parseInt(waterPreveriusTestimonies.getText()));
					}
					else{
					    waterAccauntController = new WaterAccaunt(Integer.parseInt(waterWithoutMeterAccaunt.getText()), Float.parseFloat(waterWithoutMeterTariff.getText()));
					}
					
					
					
					HousingOfficeAccount housingOfficeAccountController = new HousingOfficeAccount(Integer.parseInt(housingOfficeAccaunt.getText()),
						Float.parseFloat(housingOfficeTariff.getText()));
					
					servicesController = new Services(adrressController, new Date(), electricityAccountController
						, waterAccauntController, gasAccauntController, housingOfficeAccountController);
					
					if(servicesController.getElectricity().getDifferenceInTestimonies() < 0){
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setContentText("Электроэнергия. Некорректный ввод!");
						alert.setTitle("Некорректно заполненны поля!");
						alert.setHeaderText("Внимание!");
						alert.show();
					}
					
					if(servicesController.getGas().getDifferenceInTestimonies() < 0 ){
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setContentText("Газ. Некорректный ввод!");
						alert.setTitle("Некорректно заполненны поля!");
						alert.setHeaderText("Внимание!");
						alert.show();
						return;
					}
					
					if(servicesController.getWater().getDifferenceInTestimonies() < 0){
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setContentText("Вода. Некорректный ввод!");
						alert.setTitle("Некорректно заполненны поля!");
						alert.setHeaderText("Внимание!");
						alert.show();
						return;
					}
					
					servicesController.setTotalSum();
					float totalSum = servicesController.getTotalSum();
					
					MainWindowController.this.electricityDifferencInTestimonies.setText(String.valueOf(electricityAccountController.getDifferenceInTestimonies()));
					
					if(waterWithMeter.isSelected()){
					    MainWindowController.this.waterDifferencInTestimonies.setText(String.valueOf(waterAccauntController.getDifferenceInTestimonies()));
					    MainWindowController.this.waterSum.setText(String.valueOf(waterAccauntController.getSum()));
					}else{
					    MainWindowController.this.waterWithoutMeterSum.setText(String.valueOf(waterAccauntController.getSum()));
					}
					
					if(gazWithMeter.isSelected()){
					    MainWindowController.this.gazDifferencInTestimonies.setText(String.valueOf(gasAccauntController.getDifferenceInTestimonies()));
					    MainWindowController.this.gazSum.setText(String.valueOf(gasAccauntController.getSum()));
					}else{
					    MainWindowController.this.gazWithoutMeterSum.setText(String.valueOf(gasAccauntController.getSum()));
					}
					    
					MainWindowController.this.electricitySum.setText(String.valueOf(electricityAccountController.getSum()));
					MainWindowController.this.housingOfficeSum.setText(String.valueOf(housingOfficeAccountController.getSum()));
					
					MainWindowController.this.totalSum.setText(String.valueOf(totalSum));
				}catch(NullPointerException | NumberFormatException e){
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setContentText("Показания счетчика, № счета \nи тариф (все виды услуг)" +
							"\nнеобходимо вводить только цифры.");
					alert.setTitle("Заполните все поля корректно");
					alert.setHeaderText("Внимание!");
					alert.show();
				}
			}
		});
		
		buttonClear.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				totalSum.clear();
				adrressCity.clear();
				adrressStreet.clear();
				adrressBuilding.clear();
				adrressFlat.clear();
				gazAccaunt.clear();
				gazTariff.clear();
				gazCurrentTestimonies.clear();
				gazPreveriusTestimonies.clear();
				gazDifferencInTestimonies.clear();
				gazSum.clear();
				electricityAccaunt.clear();
				electricityTariff.clear();
				electricityTariff2.clear();
				electricityCurrentTestimonies.clear();
				electricityPreveriusTestimonies.clear();
				electricityDifferencInTestimonies.clear();
				electricitySum.clear();
				waterAccaunt.clear();
				waterTariff.clear();
				waterCurrentTestimonies.clear();
				waterPreveriusTestimonies.clear();
				waterDifferencInTestimonies.clear();
				waterSum.clear();
				housingOfficeAccaunt.clear();
				housingOfficeTariff.clear();
				housingOfficeSum.clear();
			}
		});
		buttonOpenAddress.setOnAction(new EventHandler<ActionEvent>() {
		    
		    @Override
		    public void handle(ActionEvent arg0) {
			File file = new FileChooser().showOpenDialog(main.getPrimaryStage());
			if(file != null)
				loadFromFile(file);
		    }

		   
		});
		buttonSave.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				File file = new FileChooser().showSaveDialog(main.getPrimaryStage());
				if(file != null)
					saveToFile(file);
			}
		});
		
		gazWithMeter.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				if (gazWithMeter.isSelected()){
					gazWithoutMeter.setDisable(true);
					gazWithoutMeterAccaunt.setDisable(true);
					gazWithoutMeterSum.setDisable(true);
					gazWithoutMeterTariff.setDisable(true);
					
					gazWithoutMeterAccaunt.clear();
					gazWithoutMeterSum.clear();
					gazWithoutMeterTariff.clear();
				}else{
					gazWithoutMeter.setDisable(false);
					gazWithoutMeterAccaunt.setDisable(false);
					gazWithoutMeterSum.setDisable(false);
					gazWithoutMeterTariff.setDisable(false);
					
					gazWithoutMeterAccaunt.clear();
					gazWithoutMeterSum.clear();
					gazWithoutMeterTariff.clear();
				}
				
			}
		});
		waterWithMeter.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				if (waterWithMeter.isSelected()){
					waterWithoutMeter.setDisable(true);
					waterWithoutMeterAccaunt.setDisable(true);
					waterWithoutMeterSum.setDisable(true);
					waterWithoutMeterTariff.setDisable(true);
					
					waterWithoutMeterAccaunt.clear();
					waterWithoutMeterSum.clear();
					waterWithoutMeterTariff.clear();
				}else{
					waterWithoutMeter.setDisable(false);
					waterWithoutMeterAccaunt.setDisable(false);
					waterWithoutMeterSum.setDisable(false);
					waterWithoutMeterTariff.setDisable(false);
					
					waterWithoutMeterAccaunt.clear();
					waterWithoutMeterSum.clear();
					waterWithoutMeterTariff.clear();
				}
			}
		});
		gazWithoutMeter.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				if (gazWithoutMeter.isSelected()){
					gazWithMeter.setDisable(true);
					gazAccaunt.setDisable(true);
					gazTariff.setDisable(true);
					gazCurrentTestimonies.setDisable(true);
					gazPreveriusTestimonies.setDisable(true);
					gazDifferencInTestimonies.setDisable(true);
					gazSum.setDisable(true);
					
					gazAccaunt.clear();
					gazTariff.clear();
					gazCurrentTestimonies.clear();
					gazPreveriusTestimonies.clear();
					gazDifferencInTestimonies.clear();
					gazSum.clear();
				}else{
					gazWithMeter.setDisable(false);
					gazAccaunt.setDisable(false);
					gazTariff.setDisable(false);
					gazCurrentTestimonies.setDisable(false);
					gazPreveriusTestimonies.setDisable(false);
					gazDifferencInTestimonies.setDisable(false);
					gazSum.setDisable(false);
					
					gazAccaunt.clear();
					gazTariff.clear();
					gazCurrentTestimonies.clear();
					gazPreveriusTestimonies.clear();
					gazDifferencInTestimonies.clear();
					gazSum.clear();
				}
				
			}
		});
		
		waterWithoutMeter.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				if (waterWithoutMeter.isSelected()){
					waterWithMeter.setDisable(true);
					waterAccaunt.setDisable(true);
					waterTariff.setDisable(true);
					waterCurrentTestimonies.setDisable(true);
					waterPreveriusTestimonies.setDisable(true);
					waterDifferencInTestimonies.setDisable(true);
					waterSum.setDisable(true);
					
					waterAccaunt.clear();
					waterTariff.clear();
					waterCurrentTestimonies.clear();
					waterPreveriusTestimonies.clear();
					waterDifferencInTestimonies.clear();
					waterSum.clear();
				}else{
					waterWithMeter.setDisable(false);
					waterAccaunt.setDisable(false);
					waterTariff.setDisable(false);
					waterCurrentTestimonies.setDisable(false);
					waterPreveriusTestimonies.setDisable(false);
					waterDifferencInTestimonies.setDisable(false);
					waterSum.setDisable(false);
					
					waterAccaunt.clear();
					waterTariff.clear();
					waterCurrentTestimonies.clear();
					waterPreveriusTestimonies.clear();
					waterDifferencInTestimonies.clear();
					waterSum.clear();
				}
				
			}
		});
	}
	/*В файле расположени :
	 * 1 строка :
	 * 		 городо улица дом квартира
	 * 2 строка :
	 * 		 Лиц счет газа вода электричество ЖКХ
	 * 	все данные вводятся через пробел!!!*/
	private void loadFromFile(File file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String str1 = reader.readLine();System.out.println(str1);
			String str2 = reader.readLine();System.out.println(str2);
			String[] strArr = str1.split(" ");

			adrressCity.setText(strArr[0]);
			adrressStreet.setText(strArr[1]);
			adrressBuilding.setText(strArr[2]);
			adrressFlat.setText(strArr[3]);

			strArr = str2.split(" ");
			gazAccaunt.setText(strArr[0]);
			gazWithoutMeterAccaunt.setText(strArr[0]);
			waterAccaunt.setText(strArr[1]);
			waterWithoutMeterAccaunt.setText(strArr[1]);
			electricityAccaunt.setText(strArr[2]);
			housingOfficeAccaunt.setText(strArr[3]);

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void saveToFile(File file){
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

			bufferedWriter.write("Адрес: г." + adrressCity.getText() +
					", ул." + adrressStreet.getText() +
					", д."+ adrressBuilding.getText()+
					", кв."+ adrressFlat.getText());
			bufferedWriter.newLine();
			bufferedWriter.newLine();
			if(gazWithMeter.isSelected()){
				bufferedWriter.write("Газ по счетчику: № р.с. " +  gazAccaunt.getText() +
						" тариф: " + gazTariff.getText() +
						" текущие показания счетчика: " + gazCurrentTestimonies.getText() +
						" предыдущие: " + gazPreveriusTestimonies.getText() +
						" разница: " + gazDifferencInTestimonies.getText() +
						" сумма: " + gazSum.getText());
				bufferedWriter.newLine();
				bufferedWriter.newLine();
			}else
			{
				bufferedWriter.write("Газ по тарифу: № р.с. " +  gazWithoutMeterAccaunt.getText() +
						" тариф: " + gazWithoutMeterTariff.getText() +
						" сумма: " + gazWithoutMeterSum.getText());
				bufferedWriter.newLine();
				bufferedWriter.newLine();
			}

			bufferedWriter.write("Эл.эн. по счетчику: № р.с. " +  electricityAccaunt.getText() +
					" тариф: " + electricityTariff.getText() +
					" текущие показания счетчика: " + electricityCurrentTestimonies.getText() +
					" предыдущие: " + electricityPreveriusTestimonies.getText() +
					" разница: " + electricityDifferencInTestimonies.getText() +
					" сумма: " + electricitySum.getText());
			bufferedWriter.newLine();
			bufferedWriter.newLine();
			if(waterWithMeter.isSelected()){
				bufferedWriter.write("Вода по счетчику : № р.с. " +  waterAccaunt.getText() +
						" тариф: " + waterTariff.getText() +
						" текущие показания счетчика: " + waterCurrentTestimonies.getText() +
						" предыдущие: " + waterPreveriusTestimonies.getText() +
						" разница: " + waterDifferencInTestimonies.getText() +
						" сумма: " + waterSum.getText());
				bufferedWriter.newLine();
				bufferedWriter.newLine();
			}else
			{
				bufferedWriter.write("Вода по тарифу : № р.с. " +  waterWithoutMeterAccaunt.getText() +
						" тариф: " + waterWithoutMeterTariff.getText() +
						" сумма: " + waterWithoutMeterSum.getText());
				bufferedWriter.newLine();
				bufferedWriter.newLine();
			}

			bufferedWriter.write("ЖКХ по тарифу: № р.с. " +  housingOfficeAccaunt.getText() +
					" тариф: " + housingOfficeTariff.getText() +
					" сумма: " + housingOfficeSum.getText());
			bufferedWriter.newLine();
			bufferedWriter.newLine();

			bufferedWriter.write("Общая сумма: " +  totalSum.getText());

			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMain(Main main){
		this.main = main;
	}
}
