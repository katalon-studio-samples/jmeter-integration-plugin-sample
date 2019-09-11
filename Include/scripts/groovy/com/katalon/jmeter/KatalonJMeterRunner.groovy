package com.katalon.jmeter

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.gui.ArgumentsPanel;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.report.dashboard.ReportGenerator;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jorphan.collections.HashTree;

public class KatalonJMeterRunner {
	
	private static NUM_LOOPS = 1;
	private static NUM_THREADS = 2;
	private static RAMP_UP = 1;
	private static DURATION = 10000;
	
	private static JTL_FILE = "jmeter-result.csv";

	public void run(KatalonSampler sampler) throws IOException {
		// JMeter Engine
		StandardJMeterEngine jmeter = new StandardJMeterEngine();

		JMeterUtils.loadJMeterProperties('Include/jmeter-properties/bin/jmeter.properties');
		JMeterUtils.setJMeterHome('Include/jmeter-properties/');
		JMeterUtils.initLocale();
		JMeterUtils.setProperty("output_dir", "./jmeter-report");

		// JMeter Test Plan
		HashTree testPlanTree = new HashTree();

		// Loop Controller
		LoopController loopController = new LoopController();
		loopController.setLoops(NUM_LOOPS);
		loopController.setFirst(true);
		loopController.initialize();

		// Thread Group

		org.apache.jmeter.threads.ThreadGroup threadGroup = new org.apache.jmeter.threads.ThreadGroup();
		threadGroup.setNumThreads(NUM_THREADS);
		threadGroup.setRampUp(RAMP_UP);
		threadGroup.setDuration(DURATION);
		threadGroup.setScheduler(true);
		threadGroup.setName("Main Thread Group");
		threadGroup.setSamplerController(loopController);

		// Test Plan
		TestPlan testPlan = new TestPlan("Create JMeter Script From Java Code");
		testPlan.setUserDefinedVariables((Arguments) new ArgumentsPanel().createTestElement());

		// Construct Test Plan from previously initialized elements
		testPlanTree.add(testPlan);
		HashTree threadGroupHashTree = testPlanTree.add(testPlan, threadGroup);
		threadGroupHashTree.add(sampler);

		// Add Summarizer output to get test progress in stdout like:
		// summary =      2 in   1.3s =    1.5/s Avg:   631 Min:   290 Max:   973 Err:     0 (0.00%)
		Summariser summer = null;
		String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
		if (summariserName.length() > 0) {
			summer = new Summariser(summariserName);
		}

		// Store execution results into a .jtl file
		ResultCollector logger = new ResultCollector(summer);
		logger.setFilename(JTL_FILE);
		testPlanTree.add(testPlan, logger);

		// Run Test Plan
		jmeter.configure(testPlanTree);
		jmeter.run();

		try {
			ReportGenerator reportGen = new ReportGenerator(JTL_FILE, null);
			reportGen.generate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test completed. See test.jtl file for results");
		System.out.println("Open test.jmx file in JMeter GUI to validate the code");
		System.exit(0);
	}
}
