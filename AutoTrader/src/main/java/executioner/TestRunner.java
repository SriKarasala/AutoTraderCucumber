package executioner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/features",
				glue="stepDefinitions", 
				dryRun = false
				)	
public class TestRunner extends AbstractTestNGCucumberTests {

}
