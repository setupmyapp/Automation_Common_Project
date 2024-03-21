package com.mediacorp.testRunner.Smoke;

import com.mediacorp.utils.ReportConfigurations;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(glue = "com.mediacorp.smoke.stepDefinitions", monochrome = true, dryRun = false, features = {
"src/features/java/" }, plugin = { "json:target/cucumber-json-report.json"}, tags = {"@Demo_Android"})
public class SmokeSuiteAndroid extends ReportConfigurations
{

}
