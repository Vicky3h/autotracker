package tracker;

public class Job {
	
	String item;
	String description;
	String status;
	
    public Job(String Item, String Description, String Status) {

    	this.item = Item;
    	this.description = Description;
    	this.status = Status;
    }

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}
