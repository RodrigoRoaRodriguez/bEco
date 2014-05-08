package gui;

import java.awt.Toolkit;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.TemporalAmount;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logic.Trip;
import logic.Vehicle;

public class MainWindowController {
	//Fields
	
	private ObservableList<Trip> tableData; 
	private ObservableList<Vehicle> comboBoxData;
	
	//FXML injections
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Trip, LocalDate> date;

    @FXML
    private ComboBox<Vehicle> vehiclesComboBox;

    @FXML
    private TableColumn<Trip, Double> carbondioxide;

    @FXML
    private TextField textBox;

    @FXML
    private TableColumn<Trip, Double> distance;

    @FXML
    private DatePicker datePicker;

    @FXML
    private RadioButton timeRadioButton;

    @FXML
    private TableColumn<Trip, Vehicle> vehicle;

    @FXML
    private ToggleGroup unit;

    @FXML
    private Label unitLabel;


    @FXML
    private TableColumn<Trip, Integer> time;

    @FXML
    private RadioButton distanceRadioButton;
    
    @FXML
    private TableView<Trip> table;
    
    
    //Methods called upon events

    @FXML
    void loadFile(ActionEvent event) {
    	System.out.println("Load file");
    	LocalDate date = LocalDate.now();
    	for(Vehicle vehicle: comboBoxData){
    		date = date.minusDays(1);
    		tableData.add(new Trip(10.0,vehicle,date));
    	}
    	
    }

    @FXML
    void closeFile(ActionEvent event) {
    	System.out.println("Close file");
    	tableData.clear();
    }

    @FXML
    void deleteEntry(ActionEvent event) {
    	System.out.println("Delete Entry");
    	if(table.getSelectionModel().isEmpty()){
    		Toolkit.getDefaultToolkit().beep();
    	}else{
    		int selected = table.getSelectionModel().getSelectedIndices().size();
    		int index= table.getSelectionModel().getSelectedIndex();
    		for(int i = 0; i< selected; i++) tableData.remove(index);
    		table.getSelectionModel().clearSelection();
    	}
    }

    @FXML
    void displayHelp(ActionEvent event) {
    	System.out.println("Display help");
    }

    @FXML
    void changeText(ActionEvent event) {
    	if(timeRadioButton.isSelected()) unitLabel.setText("Tid (h)");
    	else unitLabel.setText("Strecka (km)");
    }

    @FXML
    void lineChart(ActionEvent event) {
    	Stage stage = new Stage();
    	stage.setTitle("Line Chart Sample");
        //Define the axes
        stage.setTitle("Line Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Datum");       
        
        //Create the chart
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Statistik");
        //Get selected indices
        ObservableList<Integer> indices = table.getSelectionModel().getSelectedIndices();
        
        //Define CO2 series
        XYChart.Series seriesCO2 = new XYChart.Series();
        seriesCO2.setName("CO2 (g)");
        XYChart.Series seriesTime = new XYChart.Series();
        seriesTime.setName("Time (min)");
        XYChart.Series seriesDistance = new XYChart.Series();
        seriesDistance.setName("Sträcka (km)");
        
        //populating the series with data
        for(Integer index : indices){
        	Trip trip = tableData.get(index);
        	seriesCO2.getData().add(new XYChart.Data(trip.getDate().toString(), trip.getCO2(), "test"));
        	seriesTime.getData().add(new XYChart.Data(trip.getDate().toString(), trip.getTime()));
        	seriesDistance.getData().add(new XYChart.Data(trip.getDate().toString(), trip.getDistance()));
        }

        
        //Show scene
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(seriesCO2);
        lineChart.getData().add(seriesTime);
        lineChart.getData().add(seriesDistance);
       
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void addToList(ActionEvent event) {
    	if(textBox.getText().isEmpty()){
    		Toolkit.getDefaultToolkit().beep();
    	}else{  
	    	if(timeRadioButton.isSelected()){
		    	Integer time = Integer.parseInt(textBox.getText());
		    	Vehicle vehicle = vehiclesComboBox.getSelectionModel().getSelectedItem();
		    	tableData.add(new Trip(time, vehicle, LocalDate.now()));
	    	}else{
		    	Double distance = Double.parseDouble(textBox.getText());
		    	Vehicle vehicle = vehiclesComboBox.getSelectionModel().getSelectedItem();
		    	tableData.add(new Trip(distance, vehicle, datePicker.getValue()));
	    	}
    	}
    }

    //Initialization
    @FXML
    void initialize() {
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert vehiclesComboBox != null : "fx:id=\"vehiclesComboBox\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert carbondioxide != null : "fx:id=\"carbondioxide\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert textBox != null : "fx:id=\"textBox\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert distance != null : "fx:id=\"distance\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert datePicker != null : "fx:id=\"datePicker\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert timeRadioButton != null : "fx:id=\"timeRadioButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert vehicle != null : "fx:id=\"vehicle\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert unit != null : "fx:id=\"unit\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert unitLabel != null : "fx:id=\"unitLabel\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert distanceRadioButton != null : "fx:id=\"distanceRadioButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        
        //Create available means of transportation
        //sources: 
        // http://publikationswebbutik.vv.se/upload/6958/2013_002_hastighetsundersokningen_2012_resultatrapport.pdf
        // http://www.orebro.se/download/18.2e96e73312b3224f4fd80004412/Koldioxidjakten.pdf
        // http://www.gu.se/digitalAssets/1099/1099802_Grona_resor_webb.pdf
        final int CAR_SPEED = 78; 
        final int MOPED_SPEED = 30;
        final int TRAIN_SPEED = 200;
        final int PLANE_SPEED = 902;
        
        comboBoxData =FXCollections.observableArrayList(          
                new Vehicle("Fot", 4),
                new Vehicle("Cykel", 15),
                new Vehicle("Buss, diesel: 21,8g CO2/person-km",CAR_SPEED , 21.8),
                new Vehicle("Buss, biogas: 1,9g CO2/person-km",CAR_SPEED , 1.9),
                new Vehicle("Egen bil: 109g CO2/person-km",CAR_SPEED , 109),
                new Vehicle("Bil, etanol(E85): 37g CO2/person-km",CAR_SPEED , 37),
                new Vehicle("Bil, naturgas: 101g CO2/person-km",CAR_SPEED , 101),
                new Vehicle("Bil, biogas: 8g CO2/person-km",CAR_SPEED , 8),
                new Vehicle("Moped 47g CO2/person-km", MOPED_SPEED, 47),
                new Vehicle("Tåg: 0,0058g CO2/person-km",TRAIN_SPEED, 0.0058 ),
                new Vehicle("Flyg: 191g CO2/person-km ",PLANE_SPEED, 191 )
                ); 

        vehiclesComboBox.setItems(comboBoxData);
        
        //Initialize the list that will contain the table's data
        tableData = FXCollections.observableArrayList();
        //Tell columns what to fetch from the Trip Object.
        time.setCellValueFactory(new PropertyValueFactory<Trip,Integer>("time"));
        distance.setCellValueFactory(new PropertyValueFactory<Trip,Double>("distance"));
        vehicle.setCellValueFactory(new PropertyValueFactory<Trip,Vehicle>("vehicle"));
        date.setCellValueFactory(new PropertyValueFactory<Trip,LocalDate>("date"));
        carbondioxide.setCellValueFactory(new PropertyValueFactory<Trip,Double>("CO2"));
        
        //Tell the table where to get its data from.
        table.setItems(tableData);
        
        //Tell the table to allow the user to multiselect
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
