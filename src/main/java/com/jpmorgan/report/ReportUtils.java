package com.jpmorgan.report;

import com.jpmorgan.instructions.pojo.Instruction;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Class for the methods used to generate the reports
 */
public class ReportUtils {

    // Really only have two different print functions because want numbering on Entity reports and not Date reports
    static void printDateSet(Set<DateReportData> totalsSet, String title){
        System.out.println();
        System.out.println("===== " + title + " =====");
        totalsSet.forEach( (s) -> System.out.println("Date: " + s.getSettlementDate() + ": Amount: " + s.getAmount()));
        System.out.println("===== End of " + title + " =====");
    }

    static void printEntitySet(Set<EntityReportData> entityReportSet, String title){
        System.out.println();
        System.out.println("===== " + title + " =====");
        int i = 1;
        for (EntityReportData entityReportData: entityReportSet) {
            System.out.println("Rank: " + i + ", Entity: " + entityReportData.getEntityName() + ", Amount: " + entityReportData.getAmount());
            i++;
        }
        System.out.println("===== End of " + title + " =====");
    }

    static  <E> SortedSet<E> sortSet(Set<E> totalsSet){
        return new TreeSet<>(totalsSet);
    }

    static void addToDateTotal(Set<DateReportData> totalsSet, Instruction instruction){
        DateReportData dateReportData = new DateReportData(instruction.getSettlementDate(), instruction.getAmount());

        for(DateReportData d : totalsSet){
            if(d.equals(dateReportData)) {
                d.addToAmount(instruction.getAmount());
                return;
            }
        }
        totalsSet.add(dateReportData);
//        if(totalsSet.containsKey(instruction.getSettlementDate())) {
//            //System.out.println("Date " + instruction.getSettlementDate().toString() + " already exists, adding amount: " + instruction.getAmount());
//            BigDecimal newAmount = totalsSet.get(instruction.getSettlementDate()).add(instruction.getAmount());
//            totalsSet.put(instruction.getSettlementDate(), newAmount);
//        } else {
//            //System.out.println("Date " + instruction.getSettlementDate().toString() + " is new, adding amount: " + instruction.getAmount());
//            totalsSet.put(instruction.getSettlementDate(), instruction.getAmount());
//        }
    }

    static void addToEntityTotal(Set<EntityReportData> totalEntitySet, Instruction instruction){
        EntityReportData entityReportData = new EntityReportData(instruction.getEntity(), instruction.getAmount());

        for (EntityReportData e : totalEntitySet) {
            if(e.equals(entityReportData)){
                //System.out.println("Date " + instruction.getSettlementDate().toString() + " already exists, adding amount: " + instruction.getAmount());
                e.addToAmount(instruction.getAmount());
                return;
            }
        }
        totalEntitySet.add(entityReportData);
    }

    static void addToSetTotal(Set<ReportData> totalSet, Instruction instruction){
        ReportData entityReportData = new EntityReportData(instruction.getEntity(), instruction.getAmount());

        for (ReportData e : totalSet) {
            if(e.equals(entityReportData)){
                //System.out.println("Date " + instruction.getSettlementDate().toString() + " already exists, adding amount: " + instruction.getAmount());
                e.addToAmount(instruction.getAmount());
                return;
            }
        }
        totalSet.add(entityReportData);
    }
}
