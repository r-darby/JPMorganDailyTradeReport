package com.jpmorgan.instructions.enums;

public enum Currency {
    SGP,
    AED,
    SAR,
    USD,
    GBP,
    EUR;

    public static boolean contains(String s){
        for (Currency currency : Currency.values()){
            if (currency.name().equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static Currency getCurrency(String s){
        for (Currency currency : Currency.values()){
            if (currency.name().equals(s)) {
                return currency;
            }
        }
        return null;
    }
}
