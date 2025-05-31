package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src\\test\\java\\features"
		,glue={"stepdefinitions"},dryRun = false,tags= "@shopping"
		, plugin = { "html:target/cucumber-reports/report.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		)

public class RunnerClass {

}

