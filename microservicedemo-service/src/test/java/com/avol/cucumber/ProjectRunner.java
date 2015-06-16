package com.avol.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Durga on 6/15/2015.
 */
@RunWith(Cucumber.class)

@CucumberOptions(  monochrome = true,
        tags = "@tags",
        features = "src/test/resources/features/",
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" },
        dryRun = false,
        glue = "com.avol.cucumber.stepdefs" )
public class ProjectRunner {

}
