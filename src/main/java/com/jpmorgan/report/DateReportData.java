package com.jpmorgan.report;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Holds the totals for the Date + amount, for the report
 * overrides methods so it can be sorted and added to Sets (by Date)
 */
public class DateReportData implements Comparable<DateReportData>, ReportData{

    // settlementDate should be final!!! If modified, it will be lost from Set
    private LocalDate settlementDate;
    private BigDecimal amount;

    public DateReportData(LocalDate settlementDate, BigDecimal amount) {
        this.settlementDate = settlementDate;
        this.amount = amount;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal addToAmount(BigDecimal additionAmount){
        this.amount = amount.add(additionAmount);
        return this.amount;
    }

    @Override
    public int compareTo(DateReportData dRD) {
        if(dRD == this){
            return 0;
        }
        return dRD.getSettlementDate().compareTo(this.getSettlementDate()) * -1;    // Want to order the report oldest first
    }

    // Equals if the settlementDate is the same
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateReportData that = (DateReportData) o;

        return that.getSettlementDate().isEqual(this.settlementDate);
    }

    // Only on settlementDate
    @Override
    public int hashCode() {
        return 31 * settlementDate.hashCode();
    }

    @Override
    public String toString() {
        return settlementDate + " : " + amount;
    }
}
