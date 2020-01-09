package cucumberDemo;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/main/resources/features", glue={"src/main/cucumberDemo/"}, tags = "@all")
public class CucumberRunner extends AbstractTestNGCucumberTests {

}
