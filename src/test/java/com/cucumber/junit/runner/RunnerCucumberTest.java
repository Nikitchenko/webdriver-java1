package com.cucumber.junit.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "html:target/cucumber-reports/CucumberTests.html",
                "json:target/cucumber-reports/CucumberTests.json",
                "junit:target/cucumber-reports/CucumberTests.xml",
                "com.epam.reportportal.cucumber.ScenarioReporter"
        },
        monochrome = true,
        //tags = "@checkout",
        tags = "@regression",
        glue = "com.cucumber.junit",
        features = "src/test/resources/com.cucumber.junit.features"
)

public class RunnerCucumberTest {

}
