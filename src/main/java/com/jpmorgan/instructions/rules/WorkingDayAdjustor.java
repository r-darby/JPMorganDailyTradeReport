package com.jpmorgan.instructions.rules;

import com.jpmorgan.instructions.enums.Currency;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * We apply this as a date adjustor in order to calculate the next working day according to currency
 */
public class WorkingDayAdjustor {
    private static List<DayOfWeek> weekendDefault = new ArrayList<>();
    private static List<DayOfWeek> weekendGulf = new ArrayList<>();

    private static Set<Currency> gulfCurrencyList = new HashSet<>();

    public WorkingDayAdjustor(){
        weekendDefault.add(DayOfWeek.SATURDAY);
        weekendDefault.add(DayOfWeek.SUNDAY);

        weekendGulf.add(DayOfWeek.FRIDAY);
        weekendGulf.add(DayOfWeek.SATURDAY);

        gulfCurrencyList.add(Currency.AED);
        gulfCurrencyList.add(Currency.SAR);
    }

    public TemporalAdjuster getNextOrSameWorkingDaysFromCurrency(Currency currency) {
        List<DayOfWeek> weekendDays = findWeekendType(currency);
        return TemporalAdjusters.ofDateAdjuster(d -> nextOrSameWorkingDay(d, weekendDays));
    }

    private LocalDate nextOrSameWorkingDay(LocalDate date, List<DayOfWeek> weekendDays) {
        //return isWeekEnd(date) ? nextWorkingDay(date, 1) : date;

        if(isWeekEnd(date,weekendDays)){
            date = nextOrSameWorkingDay(date.plusDays(1), weekendDays);
        }
//        while(isWeekEnd(date,weekendDays)){
//            date = date.plusDays(1);
//        }
        return date;
    }

    private List<DayOfWeek> findWeekendType(Currency currency){
        if(gulfCurrencyList.contains(currency)){
            //System.out.println("Found " + currency);
            return weekendGulf;
        }
        return weekendDefault;
    }

    private boolean isWeekEnd(LocalDate date, List<DayOfWeek> weekendDays) {
        DayOfWeek dow = date.getDayOfWeek();
        boolean isWeekend = weekendDays.contains(dow);
        //System.out.println(dow + " isWeekend = " + isWeekend);
        return isWeekend;
    }
}
