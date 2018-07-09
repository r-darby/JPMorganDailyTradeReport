package com.jpmorgan.report;

import java.math.BigDecimal;

/**
 * Interface for the different reports
 * force classes to override equals() and hashCode()
 */
public interface ReportData{

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    // Should have done this!!!! Then can generify function at bottom of ReportUtils
    BigDecimal addToAmount(BigDecimal additionAmount);
}
