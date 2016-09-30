package tracker;

import java.util.ArrayList;

public class Electric extends Vehicle{
	
	ArrayList<Job> tasks;

    public Electric(String Customer, String Make, String Model, String Year, String Odometer, String Type) {
    	
		super(Customer, Make, Model, Year, Odometer, Type);
	    
	    Job taskObj1 = new Job("Battery", "Check for clean terminals (no corrosion), properly mounted housing and no damage; replace if necessary", "Open");
	    Job taskObj2 = new Job("Cooling System", "Check coolant level, add coolant if necessary, and check protection to spec -25°C", "Open");
	    Job taskObj3 = new Job("Charging Socket", "Check for damage and make sure the socket is dry", "Open");
	    
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
