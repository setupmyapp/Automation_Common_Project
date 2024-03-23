package com.mediacorp.testRunner.Smoke;

import com.mediacorp.utils.ReportConfigurations;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(glue = "com.mediacorp.Mixpanel", monochrome = true, dryRun = false, features = {
"src/features/java/" }, plugin = { "json:target/cucumber-json-report.json"}, tags = {"@Demo_LGWEBOSTV2"})
public class SmokeSuiteLGWEBOSTV2 extends ReportConfigurations
{

}
