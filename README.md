# JMeter integration plugin sample

Sample project demonstrate how to use [Katalon Studio JMeter integration plugin](https://github.com/katalon-studio/katalon-studio-jmeter-integration-plugin).

## Configuration
1. Download JMeter integration plugin by following the instructions in the [doc](https://docs.katalon.com/katalon-studio/docs/jmeter-integration.html)
2. Open your test project with Katalon Studio
3. Include your version of JMeter properties in `Include/jmeter-properties` similar to this sample
4. A JMeter runner has been implemented in [Include/scripts/groovy/com/katalon/jmeter/KatalonJMeterRunner.groovy](Include/scripts/groovy/com/katalon/jmeter/KatalonJMeterRunner.groovy) and can be customized as needed. Copy this file to your test project.

## Usage
1. Create a new test case
2. Create a new JMeter sampler by calling `com.katalon.jmeter.JMeterKeyword.createSampler` custom keyword method from the [Katalon Studio JMeter integration plugin](https://github.com/katalon-studio/katalon-studio-jmeter-integration-plugin) and passing the test action specifying a series of test steps for this test case (or just call another test case).
For example:

```groovy
def testAction = {
	WebUI.callTestCase(findTestCase('Test Cases/REST examples/Simple examples/api-2-search/Search issues/Search issues by jql'),
				[('issue_key') : ''], FailureHandling.STOP_ON_FAILURE);
};

def sampler = CustomKeywords.'com.katalon.jmeter.JMeterKeyword.createSampler'(testAction);
```

3. Run the JMeter test with the JMeter runner.
```groovy
def runner = new KatalonJMeterRunner();

runner.run(sampler);
```

4. The Katalon's test reports are available in the `Reports/<test time>/<test suite's name>` directory.
5. The JMeter's test reports are available in the `Reports/<test time>/<test suite's name>/jmeter-report` directory.

6. Happy testing!
