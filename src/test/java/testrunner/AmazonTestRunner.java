package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {".//src//test//resources//features//Amazon.feature"},
				 glue = {"stepdefinitions"},
				 tags = "@Amazon",
				 //plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
				 dryRun = false)
public class AmazonTestRunner {

}
