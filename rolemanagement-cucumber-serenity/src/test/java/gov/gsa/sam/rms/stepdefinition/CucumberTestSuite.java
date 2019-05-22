package gov.gsa.sam.rms.stepdefinition;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = { "pretty" }, features = "src/test/resources/features/", tags = { "@Login","@7"})
public class CucumberTestSuite {
	// "@RoleMigration","@3"//latest at 5/22/2019
}
