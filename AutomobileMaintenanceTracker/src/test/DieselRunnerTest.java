package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;

public class DieselRunnerTest {

		 
	public static void main(String[] args) {
		org.junit.runner.Result result = JUnitCore.runClasses(DieselTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if (result.wasSuccessful()) {
			System.out.println("Test ran successfully.");
		}
	}

}
