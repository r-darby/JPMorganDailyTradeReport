package com.jpmorgan.main;

import com.jpmorgan.instructions.data.DataSample;
import com.jpmorgan.instructions.processor.MessageProcessor;
import com.jpmorgan.report.Report;

/**
 * Main class to generate the report
 * First populate the InstructionStore by parsing sample data
 * Then generate the reports and print them out
 */
public class Main {

    public static void main(String[] args) {
        String sampleData = DataSample.getDataSample();

        MessageProcessor messageProcessor = new MessageProcessor();
        messageProcessor.processDataSample(sampleData);

        Report report = new Report();
//        report.fullReport();
        report.fullReportWithStreams();
    }
}
