package com.jpmorgan.report;

import com.jpmorgan.instructions.processor.MessageProcessor;
import com.jpmorgan.instructions.store.InstructionStore;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class ReportTest {

    MessageProcessor messageProcessor = new MessageProcessor();
    Report report = new Report();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

    @Test
    public void sortedIncomingDateSetTest() {
        // GIVEN
        String sampleData = "foo ,S , 0.50, USD, 03 May 2017, 08 May 2017, 200, 10 \n" +
                "foo ,S , 0.50, USD, 03 May 2017, 09 May 2017, 200, 10 \n" +
                "foo ,S , 0.50, USD, 03 May 2017, 09 May 2017, 200, 10 \n" +
                "foo ,S , 0.50, USD, 03 May 2017, 10 May 2017, 200, 10 \n" +
                "foo ,S , 0.50, USD, 03 May 2017, 10 May 2017, 200, 10 \n" +
                "foo ,S , 0.50, USD, 03 May 2017, 10 May 2017, 200, 10 \n" +
                "foo ,S , 0.50, USD, 03 May 2017, 12 May 2017, 200, 10 \n";

        Set<DateReportData> expectedResultMap = new LinkedHashSet<>();
        expectedResultMap.add(new DateReportData(LocalDate.parse("08 May 2017", formatter), BigDecimal.valueOf(1000.0)));
        expectedResultMap.add(new DateReportData(LocalDate.parse("09 May 2017", formatter), BigDecimal.valueOf(2000.0)));
        expectedResultMap.add(new DateReportData(LocalDate.parse("10 May 2017", formatter), BigDecimal.valueOf(3000.0)));
        expectedResultMap.add(new DateReportData(LocalDate.parse("12 May 2017", formatter), BigDecimal.valueOf(1000.0)));

        // WHEN
        InstructionStore.clearStore();
        messageProcessor.processDataSample(sampleData);
        report.fullReport();
        Set<DateReportData> sortedIncomingDateSet = report.getSortedIncomingDateSet();

        // THEN
        assertEquals(expectedResultMap, sortedIncomingDateSet);
    }

    @Test
    public void sortedOutgoingDateSetTest() {
        // GIVEN
        String sampleData = "foo ,B , 0.50, USD, 03 May 2017, 08 May 2017, 200, 10 \n" +
                "foo ,B , 0.50, USD, 03 May 2017, 08 May 2017, 200, 10 \n" +
                "foo ,B , 0.50, USD, 03 May 2017, 08 May 2017, 200, 10 \n" +
                "foo ,B , 0.50, USD, 03 May 2017, 08 May 2017, 200, 10 \n" +
                "foo ,B , 0.50, USD, 03 May 2017, 09 May 2017, 200, 10 \n" +
                "foo ,B , 0.50, USD, 03 May 2017, 11 May 2017, 200, 10 \n" +
                "foo ,B , 0.50, USD, 03 May 2017, 12 May 2017, 200, 10 \n" +
                "foo ,B , 0.50, USD, 03 May 2017, 12 May 2017, 200, 10 \n" +
                "foo ,S , 0.50, USD, 03 May 2017, 11 May 2017, 200, 10 \n" +
                "foo ,S , 0.50, USD, 03 May 2017, 11 May 2017, 200, 10 \n" +
                "foo ,S , 0.50, USD, 03 May 2017, 12 May 2017, 200, 10 \n";

        Set<DateReportData> expectedResultSet = new LinkedHashSet<>();
        expectedResultSet.add(new DateReportData(LocalDate.parse("08 May 2017", formatter), BigDecimal.valueOf(4000.0)));
        expectedResultSet.add(new DateReportData(LocalDate.parse("09 May 2017", formatter), BigDecimal.valueOf(1000.0)));
        expectedResultSet.add(new DateReportData(LocalDate.parse("11 May 2017", formatter), BigDecimal.valueOf(1000.0)));
        expectedResultSet.add(new DateReportData(LocalDate.parse("12 May 2017", formatter), BigDecimal.valueOf(2000.0)));

        // WHEN
        InstructionStore.clearStore();
        messageProcessor.processDataSample(sampleData);
        report.fullReport();
        Set<DateReportData> sortedOutgoingDateSet = report.getSortedOutgoingDateSet();

        // THEN
        assertEquals(expectedResultSet, sortedOutgoingDateSet);
    }

    @Test
    public void sortedOutgoingEntitySetTest() {
        // GIVEN
        String sampleData = "foo ,B , 0.50, USD, 03 May 2017, 08 May 2017, 200, 10 \n" +
                "foo ,B , 0.50, USD, 03 May 2017, 08 May 2017, 200, 10 \n" +
                "foo ,B , 0.50, USD, 03 May 2017, 08 May 2017, 200, 10 \n" +
                "foo ,B , 0.50, USD, 03 May 2017, 08 May 2017, 200, 10 \n" +
                "bar ,B , 0.50, USD, 03 May 2017, 12 May 2017, 200, 10 \n" +
                "aaa ,B , 0.50, USD, 03 May 2017, 12 May 2017, 200, 10 \n" +
                "aaa ,B , 0.50, USD, 03 May 2017, 11 May 2017, 200, 10 \n" +
                "bbb ,B , 0.50, USD, 03 May 2017, 11 May 2017, 200, 10 \n" +
                "bbb ,B , 0.50, USD, 03 May 2017, 11 May 2017, 200, 10 \n" +
                "bbb ,B , 0.50, USD, 03 May 2017, 12 May 2017, 200, 10 \n";

        sampleData += "foo ,S , 0.50, USD, 03 May 2017, 08 May 2017, 200, 10 \n" +
                "bar ,S , 0.50, USD, 03 May 2017, 12 May 2017, 200, 10 \n" +
                "aaa , S , 0.50, USD, 03 May 2017, 12 May 2017, 200, 10 \n" +
                "aaa ,S , 0.50, USD, 03 May 2017, 11 May 2017, 200, 10 \n" +
                "bbb ,S , 0.50, USD, 03 May 2017, 11 May 2017, 200, 10 \n" +
                "bbb ,S , 0.50, USD, 03 May 2017, 11 May 2017, 200, 10 \n";

        Set<EntityReportData> expectedResultSet = new LinkedHashSet<>();
        expectedResultSet.add(new EntityReportData("foo", BigDecimal.valueOf(4000.0)));
        expectedResultSet.add(new EntityReportData("bbb", BigDecimal.valueOf(3000.0)));
        expectedResultSet.add(new EntityReportData("aaa", BigDecimal.valueOf(2000.0)));
        expectedResultSet.add(new EntityReportData("bar", BigDecimal.valueOf(1000.0)));

        // WHEN
        InstructionStore.clearStore();
        messageProcessor.processDataSample(sampleData);
        report.fullReport();
        Set<EntityReportData> sortedOutgoingEntitySet = report.getSortedOutgoingEntitySet();

        // THEN
        assertEquals(expectedResultSet, sortedOutgoingEntitySet);
    }

    @Test
    public void sortedIncomingEntitySetTest() {
        // GIVEN
        String sampleData = "foo ,B , 0.50, USD, 03 May 2017, 08 May 2017, 200, 10 \n" +
                "foo ,B , 0.50, USD, 03 May 2017, 08 May 2017, 200, 10 \n" +
                "foo ,B , 0.50, USD, 03 May 2017, 08 May 2017, 200, 10 \n" +
                "foo ,B , 0.50, USD, 03 May 2017, 08 May 2017, 200, 10 \n" +
                "bar ,B , 0.50, USD, 03 May 2017, 12 May 2017, 200, 10 \n" +
                "aaa ,B , 0.50, USD, 03 May 2017, 12 May 2017, 200, 10 \n" +
                "aaa ,B , 0.50, USD, 03 May 2017, 11 May 2017, 200, 10 \n" +
                "bbb ,B , 0.50, USD, 03 May 2017, 11 May 2017, 200, 10 \n" +
                "bbb ,B , 0.50, USD, 03 May 2017, 11 May 2017, 200, 10 \n" +
                "bbb ,B , 0.50, USD, 03 May 2017, 12 May 2017, 200, 10 \n";

        sampleData += "foo ,S , 0.50, USD, 03 May 2017, 08 May 2017, 200, 10 \n" +
                "bar ,S , 0.50, USD, 03 May 2017, 12 May 2017, 200, 10 \n" +
                "bar ,S , 0.50, USD, 03 May 2017, 12 May 2016, 200, 10 \n" +
                "bar ,S , 0.50, USD, 03 May 2017, 12 May 2015, 200, 10 \n" +
                "bar ,S , 0.50, USD, 03 May 2017, 12 May 2014, 200, 10 \n" +
                "bar ,S , 0.50, USD, 03 May 2017, 12 May 2013, 200, 10 \n" +
                "aaa , S , 0.50, USD, 03 May 2017, 12 May 2017, 200, 10 \n" +
                "aaa ,S , 0.50, USD, 03 May 2017, 11 May 2017, 200, 10 \n" +
                "ccc ,S , 0.50, USD, 03 May 2017, 11 May 2017, 200, 10 \n" +
                "ccc ,S , 0.50, USD, 03 May 2017, 11 May 2017, 200, 10 \n" +
                "ccc ,S , 0.50, USD, 03 May 2017, 11 May 2017, 200, 10 \n" +
                "ccc ,S , 0.50, USD, 03 May 2017, 11 May 2017, 200, 10 \n";

        Set<EntityReportData> expectedResultSet = new LinkedHashSet<>();
        expectedResultSet.add(new EntityReportData("bar", BigDecimal.valueOf(5000.0)));
        expectedResultSet.add(new EntityReportData("ccc", BigDecimal.valueOf(4000.0)));
        expectedResultSet.add(new EntityReportData("aaa", BigDecimal.valueOf(2000.0)));
        expectedResultSet.add(new EntityReportData("foo", BigDecimal.valueOf(1000.0)));

        // WHEN
        InstructionStore.clearStore();
        messageProcessor.processDataSample(sampleData);
        report.fullReport();
        Set<EntityReportData> sortedIncomingEntitySet = report.getSortedIncomingEntitySet();

        // THEN
        assertEquals(expectedResultSet, sortedIncomingEntitySet);
    }
}
