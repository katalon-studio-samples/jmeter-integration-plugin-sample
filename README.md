# JMeter integration plugin sample

Sample project demonstrate how to use [Katalon Studio JMeter integration plugin](https://github.com/katalon-studio/katalon-studio-jmeter-integration-plugin).

## Companion products

### Katalon TestOps

[Katalon TestOps](https://analytics.katalon.com) is a web-based application that provides dynamic perspectives and an insightful look at your automation testing data. You can leverage your automation testing data by transforming and visualizing your data; analyzing test results; seamlessly integrating with such tools as Katalon Studio and Jira; maximizing the testing capacity with remote execution.

* Read our [documentation](https://docs.katalon.com/katalon-analytics/docs/overview.html).
* Ask a question on [Forum](https://forum.katalon.com/categories/katalon-analytics).
* Request a new feature on [GitHub](CONTRIBUTING.md).
* Vote for [Popular Feature Requests](https://github.com/katalon-analytics/katalon-analytics/issues?q=is%3Aopen+is%3Aissue+label%3Afeature-request+sort%3Areactions-%2B1-desc).
* File a bug in [GitHub Issues](https://github.com/katalon-analytics/katalon-analytics/issues).

### Katalon Studio
[Katalon Studio](https://www.katalon.com) is a free and complete automation testing solution for Web, Mobile, and API testing with modern methodologies (Data-Driven Testing, TDD/BDD, Page Object Model, etc.) as well as advanced integration (JIRA, qTest, Slack, CI, Katalon TestOps, etc.). Learn more about [Katalon Studio features](https://www.katalon.com/features/).


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
