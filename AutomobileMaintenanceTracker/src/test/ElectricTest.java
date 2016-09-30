package test;

import static org.junit.Assert.*;

import org.junit.Test;

import tracker.Electric;


public class ElectricTest {
	
	String customer = "Eric Goodman";
	String make = "Hyundai";
	String model = "Accent";
	String year = "2009";
	String odometer = "124654";
	String type = "Electric";

	Electric electricObj = new Electric(customer, make, model, year, odometer, type);
	
	@Test
	public void testElectric() {
		assertEquals(customer, electricObj.getCustomer());
		assertEquals(make, electricObj.getMake());
		assertEquals(model, electricObj.getModel());
		assertEquals(year, electricObj.getYear());
		assertEquals(odometer, electricObj.getOdometer());
		assertEquals(type, electricObj.getType());
		
	}

	
}
