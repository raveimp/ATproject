package packages.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith (Cucumber.class)
@CucumberOptions (
        features = "resources/features",
        glue = "packages.steps",
        dryRun = false,
        tags = "@Debug",
        plugin = {"pretty"}
)
public class DebugRunner {

}