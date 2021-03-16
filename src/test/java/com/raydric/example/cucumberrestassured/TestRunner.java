package com.raydric.example.cucumberrestassured;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = {"com/raydric/example/cucumberrestassured/steps"})
public class TestRunner {
}
