package com.jpmorgan.instructions.pojo;

import com.jpmorgan.instructions.enums.BuySell;
import com.jpmorgan.instructions.enums.Currency;
import com.jpmorgan.instructions.rules.WorkingDayAdjustor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * pojo to represent the instruction sent by clients to execute in intl markets
 *
 * in workplace, I'd check if these data types are always correct - for now, best guess based on sample data
 *
 * variables can only be set through constructor here, declared final so no setters - I'm assuming
 * an instruction would not be changed (or at least there would be a different process for amendments)
 */
public class Instruction {

    private final String entity;
    private final BuySell buySell;
    private final double agreedFx;
    private final Currency currency;
    private final LocalDate instructionDate;
    private final LocalDate settlementDate;
    private final int units;
    private final double pricePerUnit;

    private boolean isOutgoing;
    private BigDecimal amount;

    WorkingDayAdjustor workingDayAdjustor = new WorkingDayAdjustor();

    public Instruction(String entity, BuySell buySell, double agreedFx, Currency currency,
                       LocalDate instructionDate, LocalDate settlementDate, int units, double pricePerUnit) {
        this.entity = entity;
        this.buySell = buySell;
        this.agreedFx = agreedFx;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.settlementDate = calculateSettlementDate(settlementDate, currency);
        this.units = units;
        this.pricePerUnit = pricePerUnit;

        this.isOutgoing = calculateIsOutgoing(buySell);
        this.amount = calculateAmount(pricePerUnit, units, agreedFx);
    }

    private BigDecimal calculateAmount(double pricePerUnit, int units, double agreedFx){
        // Shaky having this here, better business logic somewhere else
        return BigDecimal.valueOf(pricePerUnit * units * agreedFx);
    }

    private LocalDate calculateSettlementDate(LocalDate settlementDate, Currency currency){
        LocalDate newSettlementDate = settlementDate.with(workingDayAdjustor.getNextOrSameWorkingDaysFromCurrency(currency));
        //System.out.println("Settlement date input: " + settlementDate.getDayOfWeek() + ", output: " + newSettlementDate.getDayOfWeek() + ", currency = " + currency);
        return newSettlementDate;
    }

    private boolean calculateIsOutgoing(BuySell buySell){
        if(buySell == BuySell.BUY){
            //System.out.println("Setting to outgoing");
            return true;
        }
        return false;
    }

    public String getEntity() {
        return entity;
    }

    public BuySell getBuySell() {
        return buySell;
    }

    public double getAgreedFx() {
        return agreedFx;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public int getUnits() {
        return units;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public boolean isOutgoing() {
        return isOutgoing;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "entity='" + entity + '\'' +
                ", buySell=" + buySell +
                ", agreedFx=" + agreedFx +
                ", currency='" + currency + '\'' +
                ", instructionDate=" + instructionDate +
                ", settlementDate=" + settlementDate +
                ", units=" + units +
                ", pricePerUnit=" + pricePerUnit +
                ", isOutgoing=" + isOutgoing +
                ", amount=" + amount +
                '}';
    }
}
