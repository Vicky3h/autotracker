package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DieselTest.class, ElectricTest.class, GasTest.class, JobTest.class })
public class AllTests {

}
