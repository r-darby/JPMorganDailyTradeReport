package com.jpmorgan.report;

import java.math.BigDecimal;

/**
 * Holds the totals for the entities, for the report
 * overrides methods so it can be sorted and added to Sets
 */
public class EntityReportData implements Comparable<EntityReportData>, ReportData{

    // entityName should be final!!! If modified, it will be lost from Set (it's not now, but still...)
    private String entityName;
    private BigDecimal amount;

    EntityReportData(String entityName, BigDecimal amount) {
        this.entityName = entityName;
        this.amount = amount;
    }

    public String getEntityName() {
        return entityName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal addToAmount(BigDecimal additionAmount){
        this.amount = amount.add(additionAmount);
        return this.amount;
    }

    @Override
    public int compareTo(EntityReportData eRD) {
        if(eRD == this){
            return 0;
        }
        return eRD.getAmount().compareTo(getAmount());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityReportData that = (EntityReportData) o;

        return entityName.equals(that.entityName);
    }

    @Override
    public int hashCode() {
        return entityName.hashCode();
    }

    @Override
    public String toString() {
        return entityName + " : " + amount;
    }
}
