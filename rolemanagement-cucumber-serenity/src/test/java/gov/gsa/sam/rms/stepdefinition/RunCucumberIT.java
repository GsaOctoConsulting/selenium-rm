package gov.gsa.sam.rms.stepdefinition;

import net.serenitybdd.cucumber.CucumberWithSerenity;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(monochrome=true, plugin = { "pretty"}, glue = {
		"gov.gsa.sam.rms.stepdefinition" }, features = "src/test/resources/features/", dryRun = false, tags = {
				"@SmokeTest"})
public class RunCucumberIT {
  // starts-with Chrome tags = {
//"@tag2"}
}
