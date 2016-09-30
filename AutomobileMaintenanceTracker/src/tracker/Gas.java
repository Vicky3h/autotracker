package tracker;

import java.util.ArrayList;

public class Gas extends Vehicle{
	
	ArrayList<Job> tasks;

    public Gas(String Customer, String Make, String Model, String Year, String Odometer, String Type) {
    	
		super(Customer, Make, Model, Year, Odometer, Type);
	    
	    Job taskObj1 = new Job("Engine Oil/ Oil Filter", "Change oil and replace filter", "Open");
	    Job taskObj2 = new Job("Brake System", "Check for damage and leaks, thickness of pads, and brake fluid level", "Open");
	    Job taskObj3 = new Job("Tires and spare wheel", "Check for wear and damage. Check tire pressure. Reset Tire Pressure Monitoring Systemr", "Open");
	    
	    ArrayList<Job> tasksArray = new ArrayList<Job>();
	    tasksArray.add(taskObj1);
	    tasksArray.add(taskObj2);
	    tasksArray.add(taskObj3);
	    
	    this.tasks = tasksArray;
		
	}

	public ArrayList<Job> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Job> tasks) {
		this.tasks = tasks;
	} 

}