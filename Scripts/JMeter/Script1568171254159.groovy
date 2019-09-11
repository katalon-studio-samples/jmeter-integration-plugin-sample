import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

import com.katalon.jmeter.KatalonJMeterRunner


def testAction = {
	WebUI.callTestCase(findTestCase('Test Cases/REST examples/Simple examples/api-2-search/Search issues/Search issues by jql'),
				[('issue_key') : ''], FailureHandling.STOP_ON_FAILURE);
	WebUI.callTestCase(findTestCase('Test Cases/REST examples/Simple examples/api-2-issue/Get issue/Get an issue by Key - 1'),
				[('issue_key') : ''], FailureHandling.STOP_ON_FAILURE);
};

def sampler = CustomKeywords.'com.katalon.jmeter.JMeterKeyword.createSampler'(testAction);

def runner = new KatalonJMeterRunner();

runner.run(sampler);
