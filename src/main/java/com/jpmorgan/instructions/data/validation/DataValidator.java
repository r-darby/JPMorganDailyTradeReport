package com.jpmorgan.instructions.data.validation;

import com.jpmorgan.instructions.enums.BuySell;
import com.jpmorgan.instructions.enums.Currency;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Validate the incoming data fields
 */
public class DataValidator {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public String validateString(String s, String fieldName) throws ParseException{
        if(s == null){
            throw new ParseException(fieldName + " is null", 0);
        }
        s = s.trim();
        if(s.length() > 0){
            return s;
        }
        throw new ParseException(fieldName + " is blank", 0);
    }

    public int validateInt(String s, String fieldName) throws ParseException{
        if(s == null){
            throw new ParseException(fieldName + " is null", 0);
        }
        try{
            return Integer.valueOf(s.trim());
        }catch(NumberFormatException e){
            throw new ParseException("Error parsing " + fieldName + ": " + e.getMessage(), 0);
        }
    }

    public double validateDouble(String s, String fieldName) throws ParseException{
        if(s == null){
            throw new ParseException(fieldName + " is null", 0);
        }
        try{
            return Double.valueOf(s.trim());
        }catch(NumberFormatException e){
            throw new ParseException("Error parsing " + fieldName + ": " + e.getMessage(), 0);
        }
    }

    public BuySell validateBuySell(String s, String fieldName) throws ParseException{
        if(s == null){
            throw new ParseException(fieldName + " is null", 0);
        }
        BuySell buySell = BuySell.getBuySell(s.trim());
        if(buySell != null){
            return buySell;
        }
        throw new ParseException(fieldName + " unknown code: " + s, 0);
    }

    public Currency validateCurrency(String s, String fieldName) throws ParseException{
        if(s == null){
            throw new ParseException(fieldName + " is null", 0);
        }
        Currency currency = Currency.getCurrency(s.trim());
        if(currency != null){
            return currency;
        }
        throw new ParseException(fieldName + " unknown code: " + s, 4);
    }

    public LocalDate validateDate(String s, String fieldName) throws ParseException{
        if(s == null){
            throw new ParseException(fieldName + " is null", 0);
        }
        try{
            return LocalDate.parse(s.trim(), formatter);
        }catch(DateTimeParseException e){
            throw new ParseException("Error parsing " + fieldName + ": " + e.getMessage(), 0);
        }
    }
}
