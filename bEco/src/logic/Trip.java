package logic;

import java.time.LocalDate;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Trip {
	SimpleIntegerProperty time; //time in minutes.
	SimpleDoubleProperty distance, CO2;
	SimpleObjectProperty<LocalDate> date;
	SimpleObjectProperty<Vehicle> vehicle;
	
	public Trip(double distance, Vehicle vehicle, LocalDate date){
		this.vehicle = new SimpleObjectProperty<>(vehicle);
		this.distance= new SimpleDoubleProperty(distance);
		this.time= new SimpleIntegerProperty(calculateTime());
		this.CO2= new SimpleDoubleProperty(calculateCO2());
		this.date = new SimpleObjectProperty<>(date); 
	}
	
	public Trip(int time, Vehicle vehicle, LocalDate date){	
		this.vehicle = new SimpleObjectProperty<>(vehicle);
		this.time= new SimpleIntegerProperty(time);
		this.distance= new SimpleDoubleProperty(calculateDistance());
		this.CO2= new SimpleDoubleProperty(calculateCO2());
		this.date = new SimpleObjectProperty<>(date); 
	}
	
	private double calculateDistance(){
		return time.get()*vehicle.get().getkmperh()/60;
	}
	
	private int calculateTime(){
		return (int) (distance.get()/vehicle.get().getkmperh()*60);
	}
	
	private double calculateCO2(){
		return distance.get()*vehicle.get().getCO2gperkm();
	}
	
	//Property getters- somehow needed for obscure reasons.
	public double getCO2() {
		return CO2.get();
	}
	public double getDistance() {
		return distance.get();
	}
	public LocalDate getDate() {
		return date.get();
	}
	public Integer getTime() {
		return time.get();
	}
	public Vehicle getVehicle() {
		return vehicle.get();
	}
}
