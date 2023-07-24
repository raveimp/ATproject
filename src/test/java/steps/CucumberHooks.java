package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import reports.Log;
import stepshelpers.ValueGenerator;

public class CucumberHooks {

    @Before
    public void before(Scenario scenario) {
        Log.setScenario(scenario);
    }

    @After
    public void after() {
        ValueGenerator.clear();
    }
}