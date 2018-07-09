package com.jpmorgan.instructions.enums;

public enum BuySell {
    BUY('B'),
    SELL('S');

    private char buySell;

    BuySell(char c){
        this.buySell = c;
    }

    public char getBuySellCode() {
        return buySell;
    }

    public static boolean contains(String s){
        for (BuySell buySell : BuySell.values()) {
            if (String.valueOf(buySell.getBuySellCode()).compareTo(s) == 0) {
                return true;
            }
        }
        return false;
    }

    public static BuySell getBuySell(String s){
        for (BuySell buySell : BuySell.values()) {
            if (String.valueOf(buySell.getBuySellCode()).compareTo(s) == 0) {
                return buySell;
            }
        }
        return null;
    }
}
