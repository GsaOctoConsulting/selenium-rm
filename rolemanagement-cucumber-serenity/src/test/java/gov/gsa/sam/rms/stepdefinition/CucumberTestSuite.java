package gov.gsa.sam.rms.stepdefinition;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = { "pretty" }, glue = {
		"gov.gsa.sam.rms.stepdefinition" }, features = "src/test/resources/features/", dryRun = false, tags = {
				"@Login","@1"})
public class CucumberTestSuite {
// selecttrue
}
