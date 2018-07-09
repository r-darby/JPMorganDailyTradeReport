package com.jpmorgan.instructions.data;

import com.jpmorgan.instructions.data.validation.DataValidator;
import com.jpmorgan.instructions.pojo.Instruction;

import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Parse each line representing an instruction into an Instruction object
 */
public class DataParser {

    DataValidator dataValidator = new DataValidator();

    public Instruction parseLine(String dataLine){
        if (dataLine == null) {
            return null;
        }

        List<String> dataList = new ArrayList<>(Arrays.asList(dataLine.split(",")));

        if(dataList.size() < 8){
            System.out.println("Error processing line: " + dataLine + " >>> Error message: Wrong amount of data");
            return null;
        }

        try{
            Instruction instruction = new Instruction(dataValidator.validateString(dataList.get(0), "Entity"),
                    dataValidator.validateBuySell(dataList.get(1), "BuySell"),
                    dataValidator.validateDouble(dataList.get(2), "AgreedFX"),
                    dataValidator.validateCurrency(dataList.get(3), "Currency"),
                    dataValidator.validateDate(dataList.get(4), "InstructionDate"),
                    dataValidator.validateDate(dataList.get(5), "SettlementDate"),
                    dataValidator.validateInt(dataList.get(6), "Units"),
                    dataValidator.validateDouble(dataList.get(7), "PricePerUnit"));
            return instruction;

//        }catch(ParseException e){
            // I did the above, but could have done below - but I did get custom error message
        }catch(ParseException|NumberFormatException|DateTimeParseException e){
            System.out.print("Error processing line: " + dataLine + " >>> Error message: ");
            System.out.println(e.getMessage());
            return null;
        }
    }
}
