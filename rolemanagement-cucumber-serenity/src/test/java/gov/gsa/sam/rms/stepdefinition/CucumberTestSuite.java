package gov.gsa.sam.rms.stepdefinition;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

import org.jboss.aerogear.security.otp.Totp;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = { "pretty" }, glue = {
		"gov.gsa.sam.rms.stepdefinition" }, features = "src/test/resources/features/", dryRun = false, tags = {
				"@RoleDefinition","@5"})
public class CucumberTestSuite {
	// "@SystemAccountEmail","@9R
	// "@T1Workspace","@32" // 433
	//	@id starts-with entity apostrophe
	// selectEntity  By.xpath("./")

//public static void main(String...args) {
//	Totp totp = new Totp("IEJOYN6TKIFCLYX4");
//	String otp = totp.now();
//	System.out.println(otp);
//}


}











