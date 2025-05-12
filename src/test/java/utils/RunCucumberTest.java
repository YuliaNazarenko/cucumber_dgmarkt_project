package utils;

import org.junit.platform.suite.api.*;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ExcludeTags("ignored")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps")
public class RunCucumberTest {
}
