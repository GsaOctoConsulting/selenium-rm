package gov.gsa.sam.rms.stepdefinition;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = { "pretty" }, features = "src/test/resources/features/", tags = {"@RoleRequest","@3"})
public class CucumberTestSuite {
	// "@SystemAccount","@5"
	//  "@SystemAccount","@6"
	//  "@SystemAccount","@7"
	
    
}
