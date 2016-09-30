package test;

import static org.junit.Assert.*;

import org.junit.Test;

import tracker.Diesel;


public class DieselTest {
	
	String customer = "Eric Goodman";
	String make = "Hyundai";
	String model = "Accent";
	String year = "2009";
	String odometer = "124654";
	String type = "Diesel";

	Diesel dieselObj = new Diesel(customer, make, model, year, odometer, type);
	
	@Test
	public void testDiesel() {
		assertEquals(customer, dieselObj.getCustomer());
		assertEquals(make, dieselObj.getMake());
		assertEquals(model, dieselObj.getModel());
		assertEquals(year, dieselObj.getYear());
		assertEquals(odometer, dieselObj.getOdometer());
		assertEquals(type, dieselObj.getType());
		
	}

	
}
