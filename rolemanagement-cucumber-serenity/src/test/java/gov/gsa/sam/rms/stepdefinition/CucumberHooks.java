package gov.gsa.sam.rms.stepdefinition;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CucumberHooks {
	@Before
	public void beforeScenario() {
	   System.out.println("-------------------------------Start of the scenario-------------------------------");
	}
	
	@After
	public void afterScenario() {
	   System.out.println("----------------------End of the scenario--------------------------");
	}
}
