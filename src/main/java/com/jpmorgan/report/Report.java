package com.jpmorgan.report;

import com.jpmorgan.instructions.pojo.Instruction;
import com.jpmorgan.instructions.store.InstructionStore;

import java.util.*;

/**
 * Class to generate the reports
 * First, populate the HashMaps and HashSets with the relevant data
 * Second, get SortedMaps from the HashMaps and SortedSets from the HashSets
 * doing this as adding in or updating SortedMaps or SortedSets has a much higher performance overhead
 * better to complete the HashMaps/Sets and then sort them once
 */
public class Report {

    private Set<DateReportData> incomingDateSet = new HashSet<>();
    private Set<DateReportData> outgoingDateSet = new HashSet<>();
    private SortedSet<DateReportData> sortedIncomingDateSet;
    private SortedSet<DateReportData> sortedOutgoingDateSet;

    private Set<EntityReportData> incomingEntitySet = new HashSet<>();
    private Set<EntityReportData> outgoingEntitySet = new HashSet<>();
    private SortedSet<EntityReportData> sortedIncomingEntitySet;
    private SortedSet<EntityReportData> sortedOutgoingEntitySet;

    public void fullReport(){
        System.out.println();
        System.out.println("Generating Report....");

        populateAllReportData();

        sortAllData();

        printAllData();
    }

    public void fullReportWithStreams(){
        populateAllReportData();

        incomingDateSet.stream().sorted().forEach(System.out::println);
    }

    private void sortAllData(){
        sortedIncomingDateSet = ReportUtils.sortSet(incomingDateSet);
        sortedOutgoingDateSet = ReportUtils.sortSet(outgoingDateSet);

        sortedIncomingEntitySet = ReportUtils.sortSet(incomingEntitySet);
        sortedOutgoingEntitySet = ReportUtils.sortSet(outgoingEntitySet);
    }

    private void printAllData(){
        ReportUtils.printDateSet(sortedIncomingDateSet, "Incoming by Date");
        ReportUtils.printDateSet(sortedOutgoingDateSet, "Outgoing by Date");

        ReportUtils.printEntitySet(sortedIncomingEntitySet, "Incoming by Entity");
        ReportUtils.printEntitySet(sortedOutgoingEntitySet, "Outgoing by Entity");
    }

    // Choosing to process all reports at once for performance purposes
    public void populateAllReportData(){
        clearReportData();

        // Iterate through instructionList and populate the various maps
        for (Instruction instruction: InstructionStore.getInstructionList()) {
            if(instruction.isOutgoing()){
                ReportUtils.addToDateTotal(outgoingDateSet, instruction);
                ReportUtils.addToEntityTotal(outgoingEntitySet, instruction);
            }else{
                ReportUtils.addToDateTotal(incomingDateSet, instruction);
                ReportUtils.addToEntityTotal(incomingEntitySet, instruction);
            }
        }
    }

    private void clearReportData(){
        incomingDateSet.clear();
        outgoingDateSet.clear();

        incomingEntitySet.clear();
        outgoingEntitySet.clear();
    }

    public SortedSet<DateReportData> getSortedIncomingDateSet() {
        return Collections.unmodifiableSortedSet(sortedIncomingDateSet);
    }

    public SortedSet<DateReportData> getSortedOutgoingDateSet() {
        return Collections.unmodifiableSortedSet(sortedOutgoingDateSet);
    }

    public SortedSet<EntityReportData> getSortedIncomingEntitySet() {
        return Collections.unmodifiableSortedSet(sortedIncomingEntitySet);
    }

    public SortedSet<EntityReportData> getSortedOutgoingEntitySet() {
        return Collections.unmodifiableSortedSet(sortedOutgoingEntitySet);
    }
}
