package test;

import static org.junit.Assert.*;

import org.junit.Test;

import tracker.Job;

public class JobTest {
	
	String item = "Battery";
	String description = "Check for clean terminals (no corrosion), properly mounted housing and no damage; replace if necessary";
	String status = "Open";

	Job singleJob = new Job(item, description, status);
	
	@Test
	public void ElectricJobTest() {
		assertEquals(item, singleJob.getItem());
		assertEquals(description, singleJob.getDescription());
		assertEquals(status, singleJob.getStatus());
	}

}
