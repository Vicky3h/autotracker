package tracker;

import java.util.ArrayList;
import java.util.UUID;

public class Vehicle {
	
	String id;
	String customer;
    String make;
    String model;
    String year;
    String odometer;
    String type;
    ArrayList<Job> tasks;
	
    public Vehicle(String Customer, String Make, String Model, String Year, String Odometer, String Type) {

    	this.id = UUID.randomUUID().toString();
    	this.customer = Customer;
    	this.make = Make;
    	this.model = Model;
    	this.year = Year;
    	this.odometer = Odometer;
    	this.type = Type;
      
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getOdometer() {
		return odometer;
	}

	public void setOdometer(String odometer) {
		this.odometer = odometer;
	}

    public ArrayList<Job> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Job> tasks) {
		this.tasks = tasks;
	}
}

