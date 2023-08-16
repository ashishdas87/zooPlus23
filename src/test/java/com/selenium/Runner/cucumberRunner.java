package com.selenium.Runner;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import org.junit.runner.JUnitCore;
    @RunWith(Cucumber.class)
    @CucumberOptions(
        plugin={"pretty", "json:Reports/cucumber.json"},features="src/main/resources/features",
        glue={"com.selenium.Stepdefination"},tags="@zooplus")
    public class cucumberRunner {


    }

