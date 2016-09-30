package test;

import static org.junit.Assert.*;

import org.junit.Test;

import tracker.Gas;


public class GasTest {
	
	String customer = "Eric Goodman";
	String make = "Hyundai";
	String model = "Accent";
	String year = "2009";
	String odometer = "124654";
	String type = "Gas";

	Gas gasObj = new Gas(customer, make, model, year, odometer, type);
	
	@Test
	public void testGas() {
		assertEquals(customer, gasObj.getCustomer());
		assertEquals(make, gasObj.getMake());
		assertEquals(model, gasObj.getModel());
		assertEquals(year, gasObj.getYear());
		assertEquals(odometer, gasObj.getOdometer());
		assertEquals(type, gasObj.getType());
		
	}

	
}
