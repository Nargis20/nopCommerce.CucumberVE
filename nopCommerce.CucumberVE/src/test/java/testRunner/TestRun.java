package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		//features =".//Features/Customers.feature",//mandatory options only one feature file
		features =".//Features/",//all the feature file will be execute
		//features ={".//Features/Customers.feature",".//Features/Login.feature"}, // specific feature file
		glue = {"stepDefinations"},
		dryRun = false, //true=all the feature having corresponding method or not
		monochrome=true, //remove all unnecessary character
		plugin = {"pretty","html:Reports/test-output"},//generate html report
		publish = true
		//tags= "@SanityTest"//only SanityTest
		//tags="@Regression"
		//tags= "@SanityTest,@Regression"//either one or another 
		//tags= "@SanityTest", "@Regression" //both and
		)

public class TestRun {

}
