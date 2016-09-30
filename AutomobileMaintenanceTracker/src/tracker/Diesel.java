package tracker;

import java.util.ArrayList;

public class Diesel extends Vehicle{
	
	ArrayList<Job> tasks;

    public Diesel(String Customer, String Make, String Model, String Year, String Odometer, String Type) {
    	
		super(Customer, Make, Model, Year, Odometer, Type);
	        
	    ArrayList<Job> tasksArray = new ArrayList<Job>();
	    tasksArray.add(new Job("Engine Oil/ Oil Filter", "Change oil and replace filter", "Open"));
	    tasksArray.add(new Job("Brake System", "Check for damage and leaks, thickness of pads, and brake fluid level", "Open"));
	    tasksArray.add(new Job("Tires and spare wheel", "Check for wear and damage. Check tire pressure. Reset Tire Pressure Monitoring Systemr", "Open"));
	    tasksArray.add(new Job("Engine compartment", "Check for leaks", "Open"));
	    
	    this.tasks = tasksArray;
		
	}

	public ArrayList<Job> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Job> tasks) {
		this.tasks = tasks;
	} 

}

