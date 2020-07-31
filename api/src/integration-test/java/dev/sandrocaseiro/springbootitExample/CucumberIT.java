package dev.sandrocaseiro.springbootitExample;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/integration-test/resources/features",
    monochrome = true,
    plugin = {"pretty", "html:target/cucumber.html"})
public class CucumberIT {

}
